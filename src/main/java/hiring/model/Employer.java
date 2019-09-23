package hiring.model;

import java.io.Serializable;
import java.util.Objects;

public class Employer extends User implements Serializable {
    private String company;
    private String address;
    private String firstName;
    private String lastName;
    private String patronymic;
    //private Map<String, Integer> vacancy;

    public Employer(String login, String password, String email, String company, String address, String firstName, String lastName, String patronymic) {
        this(login, password, email, company, address, firstName, lastName);
        this.patronymic = patronymic;
    }

    public Employer(String login, String password, String email, String company, String address, String firstName, String lastName) {
        super(login, password, email, true);
        this.company = company;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getAddress() {
        return address;
    }

    public String getCompany() {
        return company;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer employer = (Employer) o;
        return Objects.equals(company, employer.company) &&
                Objects.equals(address, employer.address) &&
                Objects.equals(firstName, employer.firstName) &&
                Objects.equals(lastName, employer.lastName) &&
                Objects.equals(patronymic, employer.patronymic);
    }

    @Override
    public int hashCode() {

        return Objects.hash(company, address, firstName, lastName, patronymic);
    }
}
