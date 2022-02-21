package by.rakovets.interview.dao;

import by.rakovets.interview.model.Answer;
import by.rakovets.interview.model.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class QuestionDaoTests {
    private static QuestionDao questionDao;

    @BeforeAll
    static void init() {
        questionDao = QuestionDaoInMemory.getInstance();
    }

    @Test
    public void readAllTest() {
        // GIVEN

        // WHEN
        List<Question> actual = questionDao.readByThemeId(1L);

        // THEN
        Assertions.assertNotNull(actual);
        Assertions.assertTrue(actual.size() > 0);
    }

    @Test
    public void readByIdTest() {
        // GIVEN
        Answer answer = new Answer("Прикладные приложения, непредусматривающие свой собственный графический интерфейс");
        Question expected = new Question(1L, "Консольные приложения", answer);

        // WHEN
        Question actual = questionDao.readById(1L);

        // THEN
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }
}
