package by.rakovets.interview.data_migration.database;

import by.rakovets.interview.data_migration.exception.InterviewDataMigrationException;
import by.rakovets.interview.data_migration.jdbc.ConnectionManager;
import by.rakovets.interview.data_migration.util.StringConstants;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitSchemaImpl implements InitSchema {
    private static final Object LOCK = new Object();
    StringConstants stringConstants = new StringConstants();
    private static InitSchemaImpl INSTANCE = null;

    private static final String CREATE_SCHEMA_INTERVIEW_STORAGE_SQL = "CREATE SCHEMA IF NOT EXISTS interview_storage";
    private static final String CREATE_TABLE_THEME_SQL = "CREATE TABLE IF NOT EXISTS interview_storage.theme " +
            "(theme_id SERIAL PRIMARY KEY," +
            " name TEXT NOT NULL UNIQUE)";
    private static final String CREATE_TABLE_QUESTION_ANSWER_SQL = "CREATE TABLE IF NOT EXISTS interview_storage.question" +
            "(question_id SERIAL PRIMARY KEY," +
            " theme_id INTEGER REFERENCES interview_storage.theme(theme_id)," +
            " question TEXT NOT NULL," +
            " answer TEXT NOT NULL)";

    public static InitSchemaImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new InitSchemaImpl();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void initSchemaForDataBase() throws InterviewDataMigrationException {
        try (Connection connection = ConnectionManager.openConnection();
             Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate(CREATE_SCHEMA_INTERVIEW_STORAGE_SQL);
            statement.executeUpdate(CREATE_TABLE_THEME_SQL);
            statement.executeUpdate(CREATE_TABLE_QUESTION_ANSWER_SQL);
            connection.commit();
        } catch (SQLException e) {
            throw new InterviewDataMigrationException(stringConstants.SCHEMA_NOT_CREATED_IN_DATABASE, e);
        }
    }
}
