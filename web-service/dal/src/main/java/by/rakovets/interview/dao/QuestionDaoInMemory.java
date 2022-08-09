package by.rakovets.interview.dao;

import by.rakovets.interview.model.Question;
import by.rakovets.interview.util.InMemoryStorage;

import java.util.List;

/**
 * Data Access Object for themes with In-memory implementation.
 */
public class QuestionDaoInMemory implements QuestionDao {
    private static final Object LOCK = new Object();
    private static QuestionDaoInMemory INSTANCE = null;
    private final InMemoryStorage IN_MEMORY_STORAGE;

    public static QuestionDaoInMemory getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new QuestionDaoInMemory();
                }
            }
        }
        return INSTANCE;
    }

    public QuestionDaoInMemory() {
        IN_MEMORY_STORAGE = InMemoryStorage.getInstance();
    }

    public List<Question> readByThemeId(long themeId) {
        return IN_MEMORY_STORAGE.readQuestionsByThemeId(themeId);
    }

    public Question readById(long id) {
        return IN_MEMORY_STORAGE.readQuestionById(id);
    }
}
