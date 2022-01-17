package by.rakovets.interview.dao;

import by.rakovets.interview.model.Theme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ThemeDaoTests {
    private static ThemeDao themeDao;

    @BeforeAll
    static void init() {
        themeDao = ThemeDaoInMemory.getInstance();
    }

    @Test
    public void readAllTest() {
        // GIVEN

        // WHEN
        List<Theme> actual = themeDao.readAll();

        // THEN
        Assertions.assertNotNull(actual);
        Assertions.assertTrue(actual.size() > 0);
    }

    @Test
    public void readByIdTest() {
        // GIVEN
        Theme expected = new Theme(1L, null, "Консольные, Desktop и клиент-серверные и web-приложения(как частный случай клиент-сервера)");

        // WHEN
        Theme actual = themeDao.readById(1L);

        // THEN
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }
}
