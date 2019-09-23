package hiring.model;

import java.io.Serializable;
import java.util.Objects;

public class Demand extends Skill implements Serializable {
    private boolean isNecessarily;

    public Demand(String name, Integer lvl, boolean isNecessarily){
        super(name,lvl);
        this.isNecessarily = isNecessarily;
    }

    public boolean isNecessarily() {
        return isNecessarily;
    }

    public void setNecessarily(boolean necessarily) {
        isNecessarily = necessarily;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demand demand = (Demand) o;
        return isNecessarily == demand.isNecessarily;
    }

    @Override
    public int hashCode() {

        return Objects.hash(isNecessarily);
    }
}
