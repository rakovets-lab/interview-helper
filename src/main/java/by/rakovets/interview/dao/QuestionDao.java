package by.rakovets.interview.dao;

import by.rakovets.interview.model.Question;

import java.util.List;

/**
 * Data Access Object for questions.
 */
public interface QuestionDao {
    /**
     * Get all question by theme id
     * @param themeId id for Theme
     * @return all questions {@link Question}
     */
    List<Question> readByThemeId(long themeId);

    /**
     * Get question by id.
     *
     * @param id unique filed for question
     * @return question {@link Question}
     */
    Question readById(long id);
}
