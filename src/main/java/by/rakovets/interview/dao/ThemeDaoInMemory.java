package by.rakovets.interview.dao;

import by.rakovets.interview.model.Theme;
import by.rakovets.interview.util.InMemoryStorage;

import java.util.List;

/**
 * Data Access Object for themes with In-memory implementation.
 */
public class ThemeDaoInMemory implements ThemeDao {
    private static final Object LOCK = new Object();
    private static ThemeDaoInMemory INSTANCE = null;
    private final InMemoryStorage IN_MEMORY_STORAGE;

    public static ThemeDaoInMemory getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new ThemeDaoInMemory();
                }
            }
        }
        return INSTANCE;
    }

    public ThemeDaoInMemory() {
        IN_MEMORY_STORAGE = InMemoryStorage.getInstance();
    }

    public List<Theme> readAll() {
        return IN_MEMORY_STORAGE.readThemes();
    }

    public Theme readById(long id) {
        return IN_MEMORY_STORAGE.readThemeById(id);
    }
}
