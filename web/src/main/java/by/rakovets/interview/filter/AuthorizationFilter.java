package by.rakovets.interview.filter;

import by.rakovets.interview.model.Role;
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

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("AuthorizationFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (isPrivatePage(req)) {
            Object role = req.getSession().getAttribute("ROLE");
            if (role != null && role.toString().equals(Role.USER.toString())) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(req.getContextPath() + "/form/login");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isPrivatePage(HttpServletRequest req) {
        return !req.getRequestURI().contains("login")
                && !req.getRequestURI().contains("registration")
                && !req.getRequestURI().contains("static");
    }
}