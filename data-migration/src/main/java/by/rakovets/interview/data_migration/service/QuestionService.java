package by.rakovets.interview.data_migration.service;

import by.rakovets.interview.data_migration.DataMigrationApplication;
import by.rakovets.interview.data_migration.dao.QuestionDao;
import by.rakovets.interview.data_migration.entity.Question;
import by.rakovets.interview.data_migration.entity.Theme;
import by.rakovets.interview.data_migration.exception.InterviewDataMigrationException;
import by.rakovets.interview.data_migration.parser.CsvFileParser;
import by.rakovets.interview.data_migration.parser.FileParser;

import java.util.List;

public class QuestionService implements Service{
    private static final Object LOCK = new Object();
    private static QuestionService INSTANCE = null;

    private final QuestionDao questionDao;
    private final ThemeService themeService;
    private final FileParser parser;

    public static QuestionService getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new QuestionService();
                }
            }
        }
        return INSTANCE;
    }

    public QuestionService() {
        this.questionDao = QuestionDao.getInstance();
        this.themeService = ThemeService.getInstance();
        this.parser = CsvFileParser.getInstance();
    }

    public void saveQuestionAnswer() throws InterviewDataMigrationException {
        List<String> themes = themeService.findAllThemesInDatabase();
        Question question = new Question();
        Theme theme = new Theme();
        Integer themeId;
        for (String[] arrayStrings : listFromFileParser()) {
            String themeName = arrayStrings[0];
            if (themes.contains(themeName)) {
                themeId = themeService.findId(themeName);
            } else {
                theme.setName(themeName);
                themeId = themeService.saveThemeAndGetThemeId(theme, themeName);
                themes.add(themeName);
            }
            question = buildQuestionAnswer(themeId, arrayStrings);
            questionDao.save(question);
        }
    }

    private static Question buildQuestionAnswer(Integer themeId, String[] arrayStrings) {
        return Question.builder()
                .theme(themeId)
                .question(arrayStrings[1])
                .answer(arrayStrings[2])
                .build();
    }

    private List<String[]> listFromFileParser() {
        List<String[]> lists;
        try {
            lists = parser.parseFile(DataMigrationApplication.fileName);
        } catch (InterviewDataMigrationException e) {
            throw new RuntimeException(e);
        }
        return lists;
    }
}
