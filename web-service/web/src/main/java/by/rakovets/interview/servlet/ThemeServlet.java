package by.rakovets.interview.servlet;

import by.rakovets.interview.model.View;
import by.rakovets.interview.service.ThemeService;
import by.rakovets.interview.service.ThemeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet({
        "/view/themes",
        "/view/themes/*",
        "/form/themes",
        "/api/themes"
})
public class ThemeServlet extends HttpServlet {
    private final ThemeService instance = ThemeServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forwardPath;
        if (req.getRequestURI().contains("/form/themes")) {
            req.setAttribute("themes", instance.readAll());
            forwardPath = View.THEME_CREATE.getViewPath();
        } else {
            if (req.getPathInfo() != null && !"/".equals(req.getPathInfo())) {
                String[] split = req.getPathInfo().split("/");
                long themeId = Long.parseLong(split[1]);
                req.setAttribute("theme", instance.readById(themeId));
                forwardPath = View.THEME_READ.getViewPath();
            } else {
                req.setAttribute("themes", instance.readAll());
                forwardPath = View.THEME_READ_ALL.getViewPath();
            }
        }
        req.getRequestDispatcher(forwardPath).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long themeId = Long.parseLong(req.getParameterValues("themeId")[0]);
        req.setAttribute("theme", instance.readById(themeId));
        req.getRequestDispatcher(View.THEME_READ.getViewPath()).forward(req, resp);
    }
}
