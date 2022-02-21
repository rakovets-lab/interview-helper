package by.rakovets.interview.util;

import by.rakovets.interview.model.Theme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class InMemoryStorageTests {
    private static InMemoryStorage inMemoryStorage;

    @BeforeAll
    static void init() {
        inMemoryStorage = InMemoryStorage.getInstance();
    }

    @Test
    public void readThemesTest() {
        // GIVEN

        // WHEN
        List<Theme> themes = inMemoryStorage.readThemes();

        // THEN
        Assertions.assertNotNull(themes);
        Assertions.assertTrue(themes.size() > 0);
    }
}
