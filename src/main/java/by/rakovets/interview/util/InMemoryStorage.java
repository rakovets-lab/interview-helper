package by.rakovets.interview.util;

import by.rakovets.interview.model.Answer;
import by.rakovets.interview.model.Question;
import by.rakovets.interview.model.Theme;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByNames;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//todo Не является примером для ООП дизайна, так как акцент в данном примере ставится на DAO
public class InMemoryStorage {
    private static final Map<Long, Theme> themeStorage = new HashMap<>();
    private static final Object LOCK = new Object();
    private static Map<Long, Question> questionStorage;
    private static InMemoryStorage INSTANCE = null;


    public static InMemoryStorage getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new InMemoryStorage();
                }
            }
        }
        return INSTANCE;
    }

    private InMemoryStorage() {
        try {
            init();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Theme> readThemes() {
        return new ArrayList<>(themeStorage.values());
    }

    public Theme readThemeById(long themeId) {
        return themeStorage.get(themeId);
    }

    public List<Question> readQuestionsByThemeId(long themeId) {
        return themeStorage.get(themeId).getQuestions();
    }

    public Question readQuestionById(long questionId) {
        return questionStorage.get(questionId);
    }

    private void init() throws FileNotFoundException {
        String fileName = "interview-questions.csv";
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException();
        }
        FileReader fileReader = new FileReader(resource.getFile());

        List<QuestionDto> questionDtoList = new CsvToBeanBuilder<QuestionDto>(fileReader)
                .withType(InMemoryStorage.QuestionDto.class)
                .build()
                .parse();

        Long themeCounter = 0L;
        Long questionCounter = 0L;
        String previousTheme = null;

        for (QuestionDto questionDto : questionDtoList) {
            if (questionDto.getTheme().equals(previousTheme)) {
                questionCounter++;
                Question question = initQuestion(questionCounter, questionDto);
                themeStorage.get(themeCounter).getQuestions().add(question);
            } else {
                themeCounter++;
                questionCounter++;
                Theme theme = initTheme(themeCounter, questionCounter, questionDto);
                themeStorage.put(themeCounter, theme);
            }

            previousTheme = questionDto.getTheme();
        }

        questionStorage = themeStorage.entrySet().stream()
                .flatMap(entry -> entry.getValue().getQuestions().stream())
                .collect(Collectors.toMap(Question::getId, question -> question));
    }

    private Theme initTheme(Long themeCounter, Long questionCounter, QuestionDto questionDto) {
        ArrayList<Question> questions = new ArrayList<>();
        Question question = initQuestion(questionCounter, questionDto);
        questions.add(question);
        Theme theme = new Theme();
        theme.setId(themeCounter);
        theme.setScope(null);
        theme.setName(questionDto.getTheme());
        theme.setQuestions(questions);
        return theme;
    }

    private Question initQuestion(Long questionCounter, QuestionDto questionDto) {
        Answer answer = new Answer();
        answer.setContent(questionDto.getAnswer());
        Question question = new Question();
        question.setId(questionCounter);
        question.setContent(questionDto.getQuestion());
        question.setAnswer(answer);
        return question;
    }

    public static class QuestionDto {
        @CsvBindByNames({
                @CsvBindByName(column = "theme"),
                @CsvBindByName(column = "Theme")
        })
        private String theme;
        @CsvBindByNames({
                @CsvBindByName(column = "question"),
                @CsvBindByName(column = "Question")
        })
        private String question;
        @CsvBindByNames({
                @CsvBindByName(column = "answer"),
                @CsvBindByName(column = "Answer")
        })
        private String answer;

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        @Override
        public String toString() {
            return "QuestionDto{" +
                    "theme='" + theme + '\'' +
                    ", question='" + question + '\'' +
                    ", answer='" + answer + '\'' +
                    '}';
        }
    }
}
