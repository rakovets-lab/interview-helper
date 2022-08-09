package by.rakovets.interview.data_migration.entity;

import java.util.Objects;

public class Theme {
    private Integer themeId;
    private String name;

    public Theme(Integer themeId, String name) {
        this.themeId = themeId;
        this.name = name;
    }

    public Theme() {
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theme theme1 = (Theme) o;
        return Objects.equals(themeId, theme1.themeId) && Objects.equals(name, theme1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(themeId, name);
    }
}
