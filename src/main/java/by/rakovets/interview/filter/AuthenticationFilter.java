package by.rakovets.interview.filter;

import by.rakovets.interview.dao.UserDaoImpl;
import by.rakovets.interview.exception.UserNotFoundException;
import by.rakovets.interview.model.AuthPath;
import by.rakovets.interview.model.User;
import by.rakovets.interview.model.View;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter({
        "/api/login",
        "/api/logout"
})
public class AuthenticationFilter implements Filter {
    UserDaoImpl instance = UserDaoImpl.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("AuthenticationFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        switch (AuthPath.of(req.getRequestURI())) {
            case LOGIN:
                login(req, resp);
                break;
            case LOGOUT:
                logout(req, resp);
                break;
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            User user = instance.findByUsernameAndPassword(req.getParameter("username"), req.getParameter("password"));
            req.getSession().isNew();
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("ROLE", user.getRole().name());
            resp.sendRedirect(req.getContextPath() + "/view/themes");
        } catch (UserNotFoundException e) {
            req.getSession().invalidate();
            req.setAttribute("error", "Not valid username or password");
            req.getRequestDispatcher(View.LOGIN.getViewPath()).forward(req, resp);
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.getRequestDispatcher(View.LOGIN.getViewPath()).forward(req, resp);
    }
}