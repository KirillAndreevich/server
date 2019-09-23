package hiring.dto;

import hiring.model.User;

import java.io.Serializable;

public class RegisterEmployerDtoRequest extends User implements Serializable {
    private String company;
    private String address;
    private String firstName;
    private String lastName;
    private String patronymic;

    public RegisterEmployerDtoRequest(String login, String password, String email, String company, String address, String firstName, String lastName, String patronymic) {
        this(login, password, email, company, address, firstName, lastName);
        this.patronymic = patronymic;
    }

    public RegisterEmployerDtoRequest(String login, String password, String email, String company, String address, String firstName, String lastName) {
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
}
