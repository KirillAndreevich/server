package hiring.database;

import hiring.model.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class DataBase {

    private static Map<UUID, Employee> employeeMap;
    private static Map<UUID, Employer> employerMap;
    private static Map<UUID, List<Vacancy>> vacancyMap;
    private static Map<String, TokenPassword> logins; // в списке лежат UUID и Password
    private static Set<String> skills;
    private static Map<UUID, List<Skill>> skillMap;
    private static Map<UUID, UUID> employee_employer;//Разобраться с тем как примет на работу

    public static void startDataBase() {
        employerMap = new HashMap<>();
        employeeMap = new HashMap<>();
        vacancyMap = new HashMap<>();
        logins = new HashMap<>();
        skills = new HashSet<>();
        skillMap = new HashMap<>();
        employee_employer = new HashMap<>();
    }

    public static void saveDataBase(File file) throws DataBaseException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(employeeMap);
            oos.writeObject(employerMap);
            oos.writeObject(vacancyMap);
            oos.writeObject(logins);
            oos.writeObject(skills);
            oos.writeObject(skillMap);
            oos.writeObject(employee_employer);
        } catch (IOException e) {
            throw new DataBaseException(DataBaseErrorCode.ERROR_SAVE_DATA_BASE, e);
        }
    }

    public static void loadDataBase(File file) throws DataBaseException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            employeeMap = (Map<UUID, Employee>) ois.readObject();//Проверить работает ли такая запись (Преобразование типа)
            employerMap = (Map<UUID, Employer>) ois.readObject();
            vacancyMap = (Map<UUID, List<Vacancy>>) ois.readObject();
            logins = (Map<String, TokenPassword>) ois.readObject();
            skills = (Set<String>) ois.readObject();
            skillMap = (Map<UUID, List<Skill>>) ois.readObject();
            employee_employer = (Map<UUID, UUID>) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new DataBaseException(DataBaseErrorCode.ERROR_LOAD_DATA_BASE, e);
        }
    }

    /*
        public static void resetDataBase() {
            employeeMap = null;
            employerMap = null;
            vacancyMap = null;
            logins = null;
            skills = null;
            skillMap = null;
        }
    */
    public static UUID insert(Employer employer) throws DataBaseException {
        if (logins.containsKey(employer.getLogin())) throw new DataBaseException(DataBaseErrorCode.MATCHING_LOGIN);
        UUID uuid = UUID.randomUUID();//Узнать как работает этот класс

        employerMap.put(uuid, employer);
        logins.put(employer.getLogin(), new TokenPassword(uuid, employer.getPassword()));
        return uuid;
    }

    public static UUID insert(Employee employee) throws DataBaseException {
        if (logins.containsKey(employee.getLogin())) throw new DataBaseException(DataBaseErrorCode.MATCHING_LOGIN);
        UUID uuid =  UUID.randomUUID();
        employeeMap.put(uuid, employee);
        logins.put(employee.getLogin(), new TokenPassword(uuid, employee.getPassword()));
        return uuid;
    }

    public static void insert(UUID token, Vacancy vacancy) {
        vacancyMap.get(token).add(vacancy);
    }

    public static void insert(UUID token, Skill skill) throws DataBaseException {
        for (Skill skill1 : skillMap.get(token)) {
            if (skill.getName().equals(skill1.getName()))
                throw new DataBaseException(DataBaseErrorCode.DUPLICATE_SKILL);
        }
        skillMap.get(token).add(skill);
        skills.add(skill.getName());
    }

    public static void deleteUser(UUID token) {
        if (employeeMap.containsKey(token)) {
            logins.remove(employeeMap.get(token).getLogin()); //Исключение на удаление неимеющегося
            skillMap.remove(token);
            employeeMap.remove(token);
        } else {
            logins.remove(employerMap.get(token).getLogin());
            vacancyMap.remove(token);
            employerMap.remove(token);
        }
    }

    public static void deleteVacancy(UUID token, int index) throws DataBaseException {
        try {
            vacancyMap.get(token).remove(index);//Исключение выход за рамки списка
        } catch (IndexOutOfBoundsException e) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_NOT_FOUND, e);
        }
    }

    public static void deleteSkill(UUID token, int index) throws DataBaseException {//Исключение выход за рамки списка
        try {
            skillMap.get(token).remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DataBaseException(DataBaseErrorCode.SKILL_NOT_FOUND, e);
        }
    }
/*
    public static void addSkills(UUID token, List<Skill> skillss) throws DataBaseException {
        for (Skill skill : skillss) {
            for (Skill skill1 : skillMap.get(token)) {
                if (skill1.getName().equals(skill.getName()))
                    throw new DataBaseException(DataBaseErrorCode.DUPLICATE_SKILL);
            }
            skills.add(skill.getName());
        }
        skillMap.get(token).addAll(skillss); // Проверить на одинаковые умения

    }

    public static void addVacancies(UUID token, List<Vacancy> vacancies) {
        vacancyMap.get(token).addAll(vacancies);
    }
*/
    public static void changeSkillLvl(UUID token, int index, Integer lvl) throws DataBaseException {//Исключение выход за рамки списка
        try {
            skillMap.get(token).get(index).setLvl(lvl); // исключение индекса
        } catch (IndexOutOfBoundsException e) {
            throw new DataBaseException(DataBaseErrorCode.SKILL_NOT_FOUND, e);
        }
    }

    public static void changeSkillName(UUID token, int index, String name) throws DataBaseException {//Исключение выход за рамки списка
        for (Skill skill1 : skillMap.get(token)) {
            if (name.equals(skill1.getName())) throw new DataBaseException(DataBaseErrorCode.DUPLICATE_SKILL);
        }
        try {
            skillMap.get(token).get(index).setName(name);
        } catch (IndexOutOfBoundsException e) {
            throw new DataBaseException(DataBaseErrorCode.SKILL_NOT_FOUND, e);
        }
        skills.add(name);
    }

    public static void changeVacancyPosition(UUID token, int index, String position) throws DataBaseException {//Исключение выход за рамки списка
        try {
            vacancyMap.get(token).get(index).setPosition(position);
        } catch (IndexOutOfBoundsException e) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_NOT_FOUND, e);
        }
    }

    public static void changeVacancySalary(UUID token, int index, Double salary) throws DataBaseException {//Исключение выход за рамки списка
        try {
            vacancyMap.get(token).get(index).setSalary(salary);
        } catch (IndexOutOfBoundsException e) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_NOT_FOUND, e);
        }
    }

    public static void addVacancyDemands(UUID token, int index, List<Demand> demands) throws DataBaseException {//Исключение выход за рамки списка
        for (Demand demand : demands) {
            for (Demand demand1 : vacancyMap.get(token).get(index).getDemands()) {
                if (demand.getName().equals(demand1.getName()))
                    throw new DataBaseException(DataBaseErrorCode.DUPLICATE_DEMAND);
            }
            skills.add(demand.getName());
        }
        try {
            vacancyMap.get(token).get(index).getDemands().addAll(demands);//проверить на идентичные вакансии
        } catch (IndexOutOfBoundsException e) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_NOT_FOUND, e);
        }
    }

    public static void deleteVacancyDemand(UUID token, int indexVacancy, int indexDemand) throws DataBaseException {//Исключение выход за рамки списка
        Vacancy vacancy;
        try {
            vacancy = vacancyMap.get(token).get(indexVacancy);
        } catch (IndexOutOfBoundsException e) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_NOT_FOUND, e);
        }
        try {
            vacancy.getDemands().remove(indexDemand);
        } catch (IndexOutOfBoundsException e) {
            throw new DataBaseException(DataBaseErrorCode.DEMAND_NOT_FOUND, e);
        }
    }

    public static void changeVacancyDemandName(UUID token, int indexVacancy, int indexDemand, String name) throws DataBaseException {//Исключение выход за рамки списка
        Vacancy vacancy;
        for (Demand demand : vacancyMap.get(token).get(indexVacancy).getDemands()) {
            if (name.equals(demand)) throw new DataBaseException(DataBaseErrorCode.DUPLICATE_DEMAND);
        }
        skills.add(name);
        try {
            vacancy = vacancyMap.get(token).get(indexVacancy);
        } catch (IndexOutOfBoundsException e) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_NOT_FOUND, e);
        }
        try {
            vacancy.getDemands().get(indexDemand).setName(name);
        } catch (IndexOutOfBoundsException e) {
            throw new DataBaseException(DataBaseErrorCode.DEMAND_NOT_FOUND, e);
        }
        skills.add(name);
    }

    public static void changeVacancyDemandLvl(UUID token, int indexVacancy, int indexDemand, Integer lvl) throws DataBaseException {//Исключение выход за рамки списка
        Vacancy vacancy;
        try {
            vacancy = vacancyMap.get(token).get(indexVacancy);
        } catch (IndexOutOfBoundsException e) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_NOT_FOUND, e);
        }
        try {
            vacancy.getDemands().get(indexDemand).setLvl(lvl);
        } catch (IndexOutOfBoundsException e) {
            throw new DataBaseException(DataBaseErrorCode.DEMAND_NOT_FOUND, e);
        }
    }

    public static void changeVacancyDemandNecessarily(UUID token, int indexVacancy, int indexDemand, boolean isNecessarily) throws DataBaseException {//Исключение выход за рамки списка
        Vacancy vacancy;
        try {
            vacancy = vacancyMap.get(token).get(indexVacancy);
        } catch (IndexOutOfBoundsException e) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_NOT_FOUND, e);
        }

        try {
            vacancy.getDemands().get(indexDemand).setNecessarily(isNecessarily);//Как быть если одно и то же искоючение, но с разными кодами ошибки
        } catch (IndexOutOfBoundsException e) {
            throw new DataBaseException(DataBaseErrorCode.DEMAND_NOT_FOUND, e);
        }
    }

    public static List<Vacancy> getVacancies(UUID token) {
        return vacancyMap.get(token);
    }

    public static List<Vacancy> getVacanciesWithActive(UUID token, boolean isActive) {
        if (isActive) {
            return vacancyMap.get(token).stream().filter(s -> s.isActive() == true).collect(Collectors.toList());
        } else return vacancyMap.get(token).stream().filter(s -> s.isActive() == false).collect(Collectors.toList());
    }

    public static boolean UserIsOnline(UUID token) throws DataBaseException {
        if (employeeMap.containsKey(token)) {
            return employeeMap.get(token).isOnline();
        } else {
            if (employerMap.containsKey(token)) {
                return employerMap.get(token).isOnline();
            } else throw new DataBaseException(DataBaseErrorCode.WRONG_TOKEN);
        }

    }

    public static void setActiveEmployee(UUID token, boolean isActive) {
        if(isActive) employee_employer.remove(token);
        employeeMap.get(token).setActive(isActive);
    }

    public static void setActiveVacancy(UUID token, int index, boolean isActive) throws DataBaseException {
        try {
            vacancyMap.get(token).get(index).setActive(isActive);
        } catch (IndexOutOfBoundsException e) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_NOT_FOUND, e);
        }
    }

    public static void loginUser(String login, String password) throws DataBaseException {
        if (!logins.containsKey(login) || !logins.get(login).getPassword().equals(password))
            throw new DataBaseException(DataBaseErrorCode.WRONG_LOGIN_OR_PASSWORD);//Исключение пароля
        employeeMap.get(logins.get(login).getToken()).setOnline();
    }
    public static User getUser(UUID token) throws DataBaseException {
        if (employeeMap.containsKey(token)) {
            return employeeMap.get(token);
        } else return employerMap.get(token);
    }

    public static void exitUser(UUID token) {
        if (employeeMap.containsKey(token)) employeeMap.get(token).exit();
        else employerMap.get(token).exit();
    }

    public static void changeCompany(UUID token, String company) {//Проверку видимо нужно вынести в лругой класс
        employerMap.get(token).setCompany(company);
    }

    public static void changeAddress(UUID token, String address) {
        employerMap.get(token).setAddress(address);
    }

    public static void changeFirstName(UUID token, String firstName) {
        if (employeeMap.containsKey(token)) employeeMap.get(token).setFirstName(firstName);
        else employerMap.get(token).setFirstName(firstName);
    }


    public static void changeLastName(UUID token, String lastName) {
        if (employeeMap.containsKey(token)) employeeMap.get(token).setLastName(lastName);
        else employerMap.get(token).setLastName(lastName);


    }

    public static void changePatronymic(UUID token, String patronymic) {
        if (employeeMap.containsKey(token)) employeeMap.get(token).setPatronymic(patronymic);
        else employerMap.get(token).setPatronymic(patronymic);

    }

    public static void changeEmail(UUID token, String email) {
        if (employeeMap.containsKey(token)) employeeMap.get(token).setEmail(email);
        else employerMap.get(token).setEmail(email);
    }

    public static void changePassword(UUID token, String password) {
        if (employeeMap.containsKey(token)) employeeMap.get(token).setPassword(password);
        else employerMap.get(token).setPassword(password);

    }

    public static List<Employee> getEmployeesHaveAllSkillsDemandedLvl(UUID token, int indexVacancy) throws DataBaseException {//Исключение выход за рамки списка
        Iterator<UUID> tokenIterator = employeeMap.keySet().iterator();
        List<Employee> employees = new ArrayList<>();
        while (tokenIterator.hasNext()) {
            boolean flag2 = true;
            UUID tokenEmployee = tokenIterator.next();
            for (Demand demand : vacancyMap.get(token).get(indexVacancy).getDemands()) {
                if (!employeeMap.get(tokenEmployee).isActive()) {
                    flag2 = false;
                    break;
                }
                boolean flag1 = false;
                for (Skill skill : skillMap.get(tokenEmployee)) {
                    if (skill.getName().equals(demand.getName()) && skill.getLvl() >= demand.getLvl()) {
                        flag1 = true;
                        break;
                    }
                }
                if (!flag1) {
                    flag2 = false;
                    break;
                }
            }
            if (flag2) employees.add(employeeMap.get(tokenEmployee));

        }
        return employees;
    }

    public static List<Employee> getEmployeesHaveAllNecessarySkillsDemandedLvl(UUID token, int indexVacancy) throws DataBaseException {//Исключение выход за рамки списка
        Iterator<UUID> tokenIterator = employeeMap.keySet().iterator();
        List<Employee> employees = new ArrayList<>();
        while (tokenIterator.hasNext()) {
            boolean flag2 = true;
            UUID tokenEmployee = tokenIterator.next();
            for (Demand demand : vacancyMap.get(token).get(indexVacancy).getDemands().stream()
                    .filter(s -> s.isNecessarily() == true).collect(Collectors.toList())) {
                if (!employeeMap.get(tokenEmployee).isActive()) {
                    flag2 = false;
                    break;
                }
                boolean flag1 = false;
                for (Skill skill : skillMap.get(tokenEmployee)) {
                    if (skill.getName().equals(demand.getName()) && skill.getLvl() >= demand.getLvl()) {
                        flag1 = true;
                        break;
                    }
                }
                if (!flag1) {
                    flag2 = false;
                    break;
                }
            }
            if (flag2) employees.add(employeeMap.get(tokenEmployee));

        }
        return employees;
    }

    public static List<Employee> getEmployeesHaveAllSkills(UUID token, int indexVacancy) throws DataBaseException {//Исключение выход за рамки списка
        Iterator<UUID> tokenIterator = employeeMap.keySet().iterator();
        List<Employee> employees = new ArrayList<>();
        while (tokenIterator.hasNext()) {
            boolean flag2 = true;
            UUID tokenEmployee = tokenIterator.next();
            try {

                for (Demand demand : vacancyMap.get(token).get(indexVacancy).getDemands()) {
                    if (!employeeMap.get(tokenEmployee).isActive()) {
                        flag2 = false;
                        break;
                    }
                    boolean flag1 = false;
                    for (Skill skill : skillMap.get(tokenEmployee)) {
                        if (skill.getName().equals(demand.getName())) {
                            flag1 = true;
                            break;
                        }
                    }
                    if (!flag1) {
                        flag2 = false;
                        break;
                    }
                }
                if (flag2) employees.add(employeeMap.get(tokenEmployee));
            } catch (IndexOutOfBoundsException e) {
                throw new DataBaseException(DataBaseErrorCode.VACANCY_NOT_FOUND, e);
            }
        }
        return employees;
    }

    public static List<Employee> getEmployeesWithOneSuitableSkillDemandedLvl(UUID token, int indexVacancy) throws DataBaseException {//Исключение выход за рамки списка
        Iterator<UUID> tokenIterator = employeeMap.keySet().iterator();
        List<Employee> employees = new ArrayList<>();
        while (tokenIterator.hasNext()) {
            UUID tokenEmployee = tokenIterator.next();
            try {


                for (Demand demand : vacancyMap.get(token).get(indexVacancy).getDemands()) {
                    if (!employeeMap.get(tokenEmployee).isActive()) {
                        break;
                    }
                    boolean flag1 = false;
                    for (Skill skill : skillMap.get(tokenEmployee)) {
                        if (skill.getName().equals(demand.getName()) && skill.getLvl() >= demand.getLvl()) {
                            flag1 = true;
                            break;
                        }
                    }
                    if (flag1) {
                        employees.add(employeeMap.get(tokenEmployee));
                        break;
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                throw new DataBaseException(DataBaseErrorCode.VACANCY_NOT_FOUND, e);
            }

        }
        return employees;
    }

    public static List<Vacancy> getSuitableVacanciesAllDemandsMyLvl(UUID token) {
        Iterator<UUID> tokenIterator = employerMap.keySet().iterator();
        List<Vacancy> vacancies = new ArrayList<>();
        while (tokenIterator.hasNext()) {
            UUID tokenEmployer = tokenIterator.next();
            for (Vacancy vacancy : vacancyMap.get(tokenEmployer)) {
                if (vacancy.isActive()) {
                    Integer count = skillMap.get(token).size();
                    for (Skill skill : skillMap.get(token)) {
                        boolean flag = false;
                        for (Demand demand : vacancy.getDemands()) {
                            if (demand.getName().equals(skill.getName()) && skill.getLvl() >= demand.getLvl()) {
                                flag = true;
                                count--;
                                break;
                            }
                        }
                        if (!flag) break;

                    }
                    if (count == 0) vacancies.add(vacancy);
                }
            }
        }
        return vacancies;
    }

    public static List<Vacancy> getSuitableVacanciesNecessaryDemandsMyLvl(UUID token) {
        Iterator<UUID> tokenIterator = employerMap.keySet().iterator();
        List<Vacancy> vacancies = new ArrayList<>();
        while (tokenIterator.hasNext()) {
            UUID tokenEmployer = tokenIterator.next();
            for (Vacancy vacancy : vacancyMap.get(tokenEmployer)) {
                if (vacancy.isActive()) {
                    Integer count = skillMap.get(token).size();
                    for (Skill skill : skillMap.get(token)) {
                        boolean flag = false;
                        for (Demand demand : vacancy.getDemands().stream().filter(s -> s.isNecessarily() == true).collect(Collectors.toList())) {
                            if (demand.getName().equals(skill.getName()) && skill.getLvl() >= demand.getLvl()) {
                                flag = true;
                                count--;
                                break;
                            }
                        }
                        if (!flag) break;

                    }
                    if (count == 0) vacancies.add(vacancy);
                }
            }
        }
        return vacancies;
    }

    public static List<Vacancy> getSuitableVacanciesAllDemandsAllLvl(UUID token) {
        Iterator<UUID> tokenIterator = employerMap.keySet().iterator();
        List<Vacancy> vacancies = new ArrayList<>();
        while (tokenIterator.hasNext()) {
            UUID tokenEmployer = tokenIterator.next();
            for (Vacancy vacancy : vacancyMap.get(tokenEmployer)) {
                if (vacancy.isActive()) {
                    Integer count = skillMap.get(token).size();
                    for (Skill skill : skillMap.get(token)) {
                        boolean flag = false;
                        for (Demand demand : vacancy.getDemands()) {
                            if (demand.getName().equals(skill.getName())) {
                                flag = true;
                                count--;
                                break;
                            }
                        }
                        if (!flag) break;

                    }
                    if (count == 0) vacancies.add(vacancy);
                }
            }
        }
        return vacancies;
    }

    public static List<Vacancy> getVacanciesWithOneSuitableDemandsMyLvl(UUID token) { //ПЕРЕПИСАТЬ!
        Iterator<UUID> tokenIterator = employerMap.keySet().iterator();
        List<Vacancy> vacancies = new ArrayList<>();
        while (tokenIterator.hasNext()) {
            UUID tokenEmployer = tokenIterator.next();
            for (Vacancy vacancy : vacancyMap.get(tokenEmployer)) {
                if (vacancy.isActive()) {
                    boolean flag = false;
                    for (Skill skill : skillMap.get(token)) {
                        for (Demand demand : vacancy.getDemands()) {
                            if (demand.getName().equals(skill.getName()) && skill.getLvl() >= demand.getLvl()) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag) break;

                    }
                    vacancies.add(vacancy);
                }
            }
        }
        return vacancies;
    }

    public static void recruit(UUID token, Integer indexVacancy, String login) throws DataBaseException{
        if (!login.contains(login)) throw new DataBaseException(DataBaseErrorCode.INVALID_LOGIN);
            try {
                employee_employer.put(logins.get(login).getToken(),token);
                vacancyMap.get(token).get(indexVacancy).setActive(false);
                employeeMap.get(logins.get(login).getToken()).setActive(false);

            } catch (IndexOutOfBoundsException e) {
                throw new DataBaseException(DataBaseErrorCode.VACANCY_NOT_FOUND, e);
            }
    }

}
