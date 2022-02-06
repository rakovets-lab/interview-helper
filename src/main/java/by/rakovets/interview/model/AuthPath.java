package by.rakovets.interview.model;

import java.util.Arrays;

public enum AuthPath {
    LOGIN("api/login"),
    LOGOUT("api/logout");

    private final String path;

    AuthPath(String path) {
        this.path = path;
    }

    public static AuthPath of(String path) {
        return Arrays.stream(AuthPath.values())
                .filter(authPath -> path != null && path.contains(authPath.path))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getPath() {
        return this.path;
    }
}
