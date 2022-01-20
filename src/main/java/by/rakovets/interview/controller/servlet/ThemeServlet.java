package by.rakovets.interview.controller.servlet;

import by.rakovets.interview.dao.ThemeDaoInMemory;
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
    public static final String VIEW_THEME_CREATE = "/WEB-INF/jsp/create-theme.jsp";
    public static final String VIEW_THEME_READ = "/WEB-INF/jsp/read-theme.jsp";
    public static final String VIEW_THEME_READ_ALL = "/WEB-INF/jsp/read-themes.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forwardPath;
        if (req.getRequestURI().contains("/form/themes")) {
            req.setAttribute("themes", ThemeDaoInMemory.getInstance().readAll());
            forwardPath = VIEW_THEME_CREATE;
        } else {
            if (req.getPathInfo() != null && !"/".equals(req.getPathInfo())) {
                String[] split = req.getPathInfo().split("/");
                long themeId = Long.parseLong(split[1]);
                req.setAttribute("theme", ThemeDaoInMemory.getInstance().readById(themeId));
                forwardPath = VIEW_THEME_READ;
            } else {
                req.setAttribute("themes", ThemeDaoInMemory.getInstance().readAll());
                forwardPath = VIEW_THEME_READ_ALL;
            }
        }
        req.getRequestDispatcher(forwardPath).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long themeId = Long.parseLong(req.getParameterValues("themeId")[0]);
        req.setAttribute("theme", ThemeDaoInMemory.getInstance().readById(themeId));
        req.getRequestDispatcher(VIEW_THEME_READ).forward(req, resp);
    }
}
