package by.rakovets.interview.data_migration.service;

import by.rakovets.interview.data_migration.dao.ThemeDao;
import by.rakovets.interview.data_migration.entity.Theme;
import by.rakovets.interview.data_migration.exception.InterviewDataMigrationException;

import java.util.List;
import java.util.stream.Collectors;

public class ThemeService implements Service {
    private static final Object LOCK = new Object();
    private static ThemeService INSTANCE = null;

    private final ThemeDao themeDao;

    public static ThemeService getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new ThemeService();
                }
            }
        }
        return INSTANCE;
    }

    public ThemeService() {
        this.themeDao = ThemeDao.getInstance();
    }
    public Integer saveThemeAndGetThemeId(Theme theme, String themeName) throws InterviewDataMigrationException {
        themeDao.save(theme);
        return themeDao.findIDThemeByName(themeName);
    }

    public List<String> findAllThemesInDatabase() throws InterviewDataMigrationException {
        return themeDao.findAll().stream()
                .map(Theme::getName)
                .collect(Collectors.toList());
    }

    public Integer findId(String themeName) throws InterviewDataMigrationException {
        return themeDao.findIDThemeByName(themeName);
    }
}
