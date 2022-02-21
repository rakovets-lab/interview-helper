package by.rakovets.interview.service;

import by.rakovets.interview.model.Theme;

import java.util.List;

/**
 * Service for working with Themes.
 */
public interface ThemeService {
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
