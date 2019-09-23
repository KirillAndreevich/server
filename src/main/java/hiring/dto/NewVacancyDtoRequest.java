package hiring.dto;


import hiring.model.Demand;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class NewVacancyDtoRequest implements Serializable {
    private String position;
    private Double salary;
    private List<Demand> demands;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<Demand> getDemands() {
        return demands;
    }

    public void setDemands(List<Demand> demands) {
        this.demands = demands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewVacancyDtoRequest that = (NewVacancyDtoRequest) o;
        return Objects.equals(position, that.position) &&
                Objects.equals(salary, that.salary) &&
                Objects.equals(demands, that.demands);
    }

    @Override
    public int hashCode() {

        return Objects.hash(position, salary, demands);
    }
}
