package by.rakovets.interview.model;

import java.util.List;
import java.util.Objects;

public class Theme {
    private long id;
    private String scope;
    private String name;
    private List<Question> questions;

    public Theme() {
    }

    public Theme(long id, String scope, String name) {
        this.id = id;
        this.scope = scope;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theme theme = (Theme) o;
        return id == theme.id && Objects.equals(scope, theme.scope) && name.equals(theme.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scope, name);
    }
}
