package by.rakovets.interview.model;

import java.util.Objects;

public class Answer {
    private String content;

    public Answer() {
    }

    public Answer(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return content.equals(answer.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
