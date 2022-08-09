package by.rakovets.interview.data_migration.dao;

import by.rakovets.interview.data_migration.entity.Theme;
import by.rakovets.interview.data_migration.exception.InterviewDataMigrationException;
import by.rakovets.interview.data_migration.jdbc.ConnectionPool;
import by.rakovets.interview.data_migration.util.StringConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ThemeDao implements Dao<Theme> {
    private static final Object LOCK = new Object();
    StringConstants stringConstants = new StringConstants();
    private static ThemeDao INSTANCE = null;

    private static final String THEME_ID = "theme_id";
    private static final String THEME_NAME = "name";

    private static final String SAVE_THEME_SQL = "INSERT INTO interview_storage.theme(name) VALUES (?)";
    private static final String FIND_ALL_THEMES_SQL = "SELECT theme_id, name FROM interview_storage.theme";
    private static final String FIND_ID_BY_THEME_NAME_SQL = FIND_ALL_THEMES_SQL + " WHERE name = ?";

    public static ThemeDao getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new ThemeDao();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void save(Theme theme) throws InterviewDataMigrationException {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_THEME_SQL)) {
            preparedStatement.setString(1, theme.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new InterviewDataMigrationException(stringConstants.ENTITY_NOT_SAVE, e);
        }
    }

    public List<Theme> findAll() throws InterviewDataMigrationException {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_THEMES_SQL)) {
            List<Theme> themes = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                themes.add(buildTheme(resultSet));
            }
            return themes;
        } catch (SQLException e) {
            throw new InterviewDataMigrationException(stringConstants.ENTITY_NOT_FOUND, e);
        }
    }

    public Integer findIDThemeByName(String themeName) throws InterviewDataMigrationException {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ID_BY_THEME_NAME_SQL)) {
            preparedStatement.setString(1, themeName);
            Theme theme = null;
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                theme = buildTheme(resultSet);
            }
            return Objects.requireNonNull(theme).getThemeId();
        } catch (SQLException e) {
            throw new InterviewDataMigrationException(stringConstants.ENTITY_NOT_FOUND, e);
        }
    }

    private Theme buildTheme(ResultSet resultSet) throws SQLException {
        return new Theme(
                resultSet.getInt(THEME_ID),
                resultSet.getString(THEME_NAME)
        );
    }
}
