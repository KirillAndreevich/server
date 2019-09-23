package hiring.dto;

import hiring.model.User;

import java.io.Serializable;
import java.util.Objects;

public class RegisterEmployeeDtoRequest extends User implements Serializable {
    private String firstName;
    private String lastName;
    private String patronymic;

    public RegisterEmployeeDtoRequest(String login, String password, String email, String firstName, String lastName) {
        super(login, password, email, true);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public RegisterEmployeeDtoRequest(String login, String password, String email, String firstName, String lastName, String patronymic) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterEmployeeDtoRequest that = (RegisterEmployeeDtoRequest) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(patronymic, that.patronymic);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, patronymic);
    }
}
