package by.rakovets.interview.service;

import by.rakovets.interview.dao.ThemeDao;
import by.rakovets.interview.dao.ThemeDaoInMemory;
import by.rakovets.interview.model.Theme;

import java.util.List;

public class ThemeServiceImpl implements ThemeService {
    private static final Object LOCK = new Object();
    private static ThemeServiceImpl INSTANCE = null;

    private final ThemeDao themeDao;

    public static ThemeServiceImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new ThemeServiceImpl();
                }
            }
        }
        return INSTANCE;
    }

    public ThemeServiceImpl() {
        this.themeDao = ThemeDaoInMemory.getInstance();
    }

    @Override
    public List<Theme> readAll() {
        return themeDao.readAll();
    }

    @Override
    public Theme readById(long id) {
        return themeDao.readById(id);
    }
}
