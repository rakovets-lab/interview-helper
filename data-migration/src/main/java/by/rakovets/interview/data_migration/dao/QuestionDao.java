package by.rakovets.interview.data_migration.dao;

import by.rakovets.interview.data_migration.entity.Question;
import by.rakovets.interview.data_migration.exception.InterviewDataMigrationException;
import by.rakovets.interview.data_migration.jdbc.ConnectionPool;
import by.rakovets.interview.data_migration.util.StringConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionDao implements Dao<Question> {
    private static final Object LOCK = new Object();
    StringConstants stringConstants = new StringConstants();
    private static QuestionDao INSTANCE = null;

    private static final String SAVE_QUESTION_ANSWER_SQL = "INSERT INTO interview_storage.question(theme_id, question, answer) " +
            "VALUES ( ?, ?, ?)";

    public static QuestionDao getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new QuestionDao();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void save(Question question) throws InterviewDataMigrationException {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_QUESTION_ANSWER_SQL)) {
            settingValuesInPreparedStatement(question, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new InterviewDataMigrationException(stringConstants.ENTITY_NOT_SAVE, e);
        }
    }

    private static void settingValuesInPreparedStatement(Question question, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, question.getThemeId());
        preparedStatement.setString(2, question.getQuestion());
        preparedStatement.setString(3, question.getAnswer());
    }
}
