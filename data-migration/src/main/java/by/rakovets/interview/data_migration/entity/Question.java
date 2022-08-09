package by.rakovets.interview.data_migration.entity;

import java.util.Objects;

public class Question {
    private Integer questionId;
    private Integer themeId;
    private String question;
    private String answer;

    public Question(Integer questionId, Integer themeId, String question, String answer) {
        this.questionId = questionId;
        this.themeId = themeId;
        this.question = question;
        this.answer = answer;
    }

    public Question() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer answerId;
        private Integer theme;
        private String question;
        private String answer;

        public Builder answerId(Integer answerId) {
            this.answerId = answerId;
            return this;
        }

        public Builder theme(Integer theme) {
            this.theme = theme;
            return this;
        }

        public Builder question(String question) {
            this.question = question;
            return this;
        }

        public Builder answer(String answer) {
            this.answer = answer;
            return this;
        }

        public Question build() {
            return new Question(answerId, theme, question, answer);
        }
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question that = (Question) o;
        return Objects.equals(questionId, that.questionId) && Objects.equals(themeId, that.themeId) && Objects.equals(question, that.question) && Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, themeId, question, answer);
    }
}
