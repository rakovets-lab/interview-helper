package by.rakovets.interview.model;

public enum View {
    REGISTRATION("/WEB-INF/jsp/registration.jsp"),
    LOGIN("/WEB-INF/jsp/login.jsp"),
    THEME_CREATE( "/WEB-INF/jsp/create-theme.jsp"),
    THEME_READ("/WEB-INF/jsp/read-theme.jsp"),
    THEME_READ_ALL("/WEB-INF/jsp/read-themes.jsp"),
    ;

    private String path;

    View(String path) {
        this.path = path;
    }

    public String getViewPath() {
        return this.path;
    }
}
