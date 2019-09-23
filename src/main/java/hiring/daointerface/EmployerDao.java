package hiring.daointerface;

import hiring.database.DataBaseException;
import hiring.model.Demand;
import hiring.model.Employee;
import hiring.model.Employer;
import hiring.model.Vacancy;

import java.util.List;
import java.util.UUID;

public interface EmployerDao{
    UUID insert(Employer employer) throws DataBaseException;
    Employer getEmployer(UUID token) throws DataBaseException, DaoException;
    void changeFirstName(UUID token, String firstName) throws DataBaseException, DaoException;

    void changeLastName(UUID token, String lastName) throws DataBaseException, DaoException;

    void changePatronymic(UUID token, String patronymic) throws DataBaseException, DaoException;

    void insert(UUID token, Vacancy vacancy) throws DataBaseException, DaoException;

    void recruit(UUID token, Integer indexVacancy, String login) throws DataBaseException,DaoException;

    //void addVacancies(UUID token, List<Vacancy> vacancies) throws DataBaseException, DaoException;

    void deleteVacancy(UUID token, Integer number) throws DataBaseException, DaoException;

    void changeVacancyPosition(UUID token, Integer index, String position) throws DataBaseException, DaoException;

    void changeVacancySalary(UUID token, Integer index, Double salary) throws DataBaseException, DaoException;

    void addVacancyDemands(UUID token, Integer index, List<Demand> demands) throws DataBaseException, DaoException;

    void deleteVacancyDemand(UUID token, Integer indexVacancy, Integer indexDemand) throws DataBaseException, DaoException;

    void changeVacancyDemandName(UUID token, Integer indexVacancy, Integer indexDemand, String name) throws DataBaseException, DaoException;

    void changeVacancyDemandLvl(UUID token, Integer indexVacancy, Integer indexDemand, Integer lvl) throws DataBaseException, DaoException;

    void changeVacancyDemandNecessarily(UUID token, Integer indexVacancy, Integer indexDemand, boolean isNecessarily) throws DataBaseException, DaoException;

    List<Vacancy> getVacancies(UUID token) throws DataBaseException, DaoException;

    List<Vacancy> getVacanciesWithActive(UUID token, boolean isActive) throws DataBaseException, DaoException;

    void setActiveVacancy(UUID token, Integer index, boolean isActive) throws DataBaseException, DaoException;

    void changeCompany(UUID token, String company) throws DataBaseException, DaoException;

    void changeAddress(UUID token, String address) throws DataBaseException, DaoException;

    List<Employee> getEmployeesHaveAllSkillsDemandedLvl(UUID token, Integer indexVacancy) throws DataBaseException, DaoException;

    List<Employee> getEmployeesHaveAllNecessarySkillsDemandedLvl(UUID token, Integer indexVacancy) throws DataBaseException, DaoException;

    List<Employee> getEmployeesHaveAllSkills(UUID token, Integer indexVacancy) throws DataBaseException, DaoException;

    List<Employee> getEmployeesWithOneSuitableSkillDemandedLvl(UUID token, Integer indexVacancy) throws DataBaseException, DaoException;

}
