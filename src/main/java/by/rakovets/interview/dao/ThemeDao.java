package by.rakovets.interview.dao;

import by.rakovets.interview.model.Theme;

import java.util.List;

/**
 * Data Access Object for themes.
 */
public interface ThemeDao {
    /**
     * Get all themes.
     *
     * @return all themes {@link Theme}
     */
    List<Theme> readAll();

    /**
     * Get theme by id.
     *
     * @param id unique filed for theme
     * @return theme {@link Theme}
     */
    Theme readById(long id);
}
