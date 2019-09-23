package hiring.model;

import java.io.Serializable;
import java.util.Objects;

public class Skill implements Serializable {
    private String name;
    private Integer lvl;

    public Skill(String name, Integer lvl){
        this.name = name;
        this.lvl = lvl;
    }

    public Integer getLvl() {
        return lvl;
    }

    public String getName() {
        return name;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(name, skill.name) &&
                Objects.equals(lvl, skill.lvl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, lvl);
    }
}
