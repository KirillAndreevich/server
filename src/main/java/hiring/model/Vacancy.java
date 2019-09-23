package hiring.model;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Vacancy implements Serializable {
    private String position;
    private Double salary;
    private List<Demand> demands;
    private boolean active = true;

    public Vacancy(String position, Double salary, List<Demand> demands){
        this.demands = demands;
        this.position = position;
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public List<Demand> getDemands() {
        return demands;
    }

    public String getPosition() {
        return position;
    }

    public void setDemands(List<Demand> demands) {
        this.demands = demands;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return active == vacancy.active &&
                Objects.equals(position, vacancy.position) &&
                Objects.equals(salary, vacancy.salary) &&
                Objects.equals(demands, vacancy.demands);
    }

    @Override
    public int hashCode() {

        return Objects.hash(position, salary, demands, active);
    }
}
