package by.rakovets.interview.model;

public enum View {
    REGISTRATION("/WEB-INF/jsp/registration.jsp"),
    LOGIN("/WEB-INF/jsp/login.jsp"),
    ;

    private String path;

    View(String path) {
        this.path = path;
    }

    public String getViewPath() {
        return this.path;
    }
}
