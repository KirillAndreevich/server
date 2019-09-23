package hiring.dto;


public class RegisterEmployeeDtoResponse {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;
    private String email;
    private boolean active;

    public RegisterEmployeeDtoResponse(String login, String email, String firstName, String lastName,boolean active, String patronymic) {
        this.login = login;
        this.email = email;
        this.active = active;
        this.patronymic = patronymic;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
