package hiring.dto;

import hiring.model.Demand;
import hiring.model.Skill;
import hiring.model.Vacancy;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class DTO implements Serializable {
    private String login;
    private String password;
    private String email;
    private boolean online;
    private UUID token;
    private String position;
    private Double salary;
    private List<Demand> demands;
    private boolean active;
    private String name;
    private Integer lvl;
    private boolean isNecessarily;
    private String company;
    private String address;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String file;
    private Integer number;
    private List<Skill> skillss;
    private List<Vacancy> vacancies;
    private Integer numberVacancy;
    private Integer numberDemand;
    private Vacancy vacancy;
    private Skill skill;

    public DTO() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public boolean isNecessarily() {
        return isNecessarily;
    }

    public void setNecessarily(boolean necessarily) {
        isNecessarily = necessarily;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Skill> getSkillss() {
        return skillss;
    }

    public void setSkillss(List<Skill> skillss) {
        this.skillss = skillss;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public Integer getNumberVacancy() {
        return numberVacancy;
    }

    public void setNumberVacancy(Integer numberVacancy) {
        this.numberVacancy = numberVacancy;
    }

    public Integer getNumberDemand() {
        return numberDemand;
    }

    public void setNumberDemand(Integer numberDemand) {
        this.numberDemand = numberDemand;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
