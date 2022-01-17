package by.rakovets.interview.model;

import java.util.Objects;

public class Question {
    private long id;
    private String content;
    private Answer answer;

    public Question() {
    }

    public Question(long id, String content, Answer answer) {
        this.id = id;
        this.content = content;
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return id == question.id && content.equals(question.content) && answer.equals(question.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, answer);
    }
}
