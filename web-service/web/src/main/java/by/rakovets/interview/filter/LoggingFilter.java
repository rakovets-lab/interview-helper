package by.rakovets.interview.filter;

import by.rakovets.interview.model.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalTime;

@WebFilter(filterName = "LoggingFilter")
public class LoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("LoggingFilter");
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            try {
                chain.doFilter(request, response);
            } finally {
                auditHttpRequest((HttpServletRequest) request);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private void auditHttpRequest(HttpServletRequest req) {
        print("URL: " + req.getRequestURL().toString());
        HttpSession session = req.getSession();
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            print(String.format("Username: %s", user.getUsername()));
        }
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                print(String.format("Cookie: %s=%s", cookie.getName(), cookie.getValue()));
            }
        }
    }

    private void print(String text) {
        System.out.printf("[Audit] - %s - %s\n", LocalTime.now(), text);
    }
}
