package hiring.dto;

import java.io.Serializable;
import java.util.Objects;

public class NewSkillDtoRequest implements Serializable {
    private String name;
    private Integer lvl;

    public NewSkillDtoRequest(String name, Integer lvl) {
        this.name = name;
        this.lvl = lvl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewSkillDtoRequest that = (NewSkillDtoRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(lvl, that.lvl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, lvl);
    }

}
