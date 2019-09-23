package hiring.model;

import java.io.Serializable;
import java.util.Objects;

public class Employee extends User implements Serializable {
    private String firstName;
    private String lastName;
    private String patronymic;
    //private Map<String, Integer> skills; //Где инициализировать умения?
    private boolean active = true;


    public Employee(String login, String password, String email, String firstName, String lastName) {
        super(login, password, email, true);
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = true;

    }

    public Employee(String login, String password, String email, String firstName, String lastName, String patronymic) {
        this(login, password, email, firstName, lastName);
        this.patronymic = patronymic;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPatronymic() {
        return this.patronymic;
    }

    public String getFullName() {
        return this.patronymic == null || this.patronymic.equals("") ? firstName + " " + lastName : firstName + " " + lastName + " " + patronymic;
    }
/*
    public void addSkill(String skill, Integer value) {
        this.skills.put(skill, value);
    }

    public void removeSkill(String skill) {
        this.skills.remove(skill);
    }

    public Map<String, Integer> getSkills() {
        return this.skills;
    }
*/
    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return this.active;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return active == employee.active &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(patronymic, employee.patronymic);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, patronymic, active);
    }
}
