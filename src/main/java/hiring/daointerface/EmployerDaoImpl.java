package hiring.daointerface;

import hiring.database.DataBase;
import hiring.database.DataBaseException;
import hiring.model.Demand;
import hiring.model.Employee;
import hiring.model.Employer;
import hiring.model.Vacancy;

import java.util.List;
import java.util.UUID;

public class EmployerDaoImpl implements EmployerDao {
    public EmployerDaoImpl(){

    }
    public Employer getEmployer(UUID token) throws DataBaseException, DaoException{
        if (DataBase.UserIsOnline(token)) {
            return (Employer) DataBase.getUser(token);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }
    public UUID insert(Employer employer) throws DataBaseException {
        return DataBase.insert(employer);
    }

    public void recruit(UUID token, Integer numberVacancy, String login) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.recruit(token, numberVacancy - 1, login);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void changeFirstName(UUID token, String firstName) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.changeFirstName(token, firstName);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void changeLastName(UUID token, String lastName) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.changeLastName(token, lastName);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void changePatronymic(UUID token, String patronymic) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.changePatronymic(token, patronymic);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void insert(UUID token, Vacancy vacancy) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.insert(token, vacancy);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }
/*
    public void addVacancies(UUID token, List<Vacancy> vacancies) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.addVacancies(token, vacancies);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }
*/
    public void deleteVacancy(UUID token, Integer number) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.deleteVacancy(token, number - 1);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void changeVacancyPosition(UUID token, Integer number, String position) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.changeVacancyPosition(token, number - 1, position);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void changeVacancySalary(UUID token, Integer number, Double salary) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.changeVacancySalary(token, number - 1, salary);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void addVacancyDemands(UUID token, Integer number, List<Demand> demands) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.addVacancyDemands(token, number - 1, demands);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void deleteVacancyDemand(UUID token, Integer numberVacancy, Integer numberDemand) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.deleteVacancyDemand(token, numberVacancy - 1, numberDemand - 1);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void changeVacancyDemandName(UUID token, Integer numberVacancy, Integer numberDemand, String name)
            throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.changeVacancyDemandName(token, numberVacancy - 1, numberDemand - 1, name);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void changeVacancyDemandLvl(UUID token, Integer numberVacancy, Integer numberDemand, Integer lvl)
            throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.changeVacancyDemandLvl(token, numberVacancy - 1, numberDemand - 1, lvl);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void changeVacancyDemandNecessarily(UUID token, Integer numberVacancy, Integer numberDemand, boolean isNecessarily)
            throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.changeVacancyDemandNecessarily(token, numberVacancy - 1, numberDemand - 1, isNecessarily);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public List<Vacancy> getVacancies(UUID token) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            return DataBase.getVacancies(token);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public List<Vacancy> getVacanciesWithActive(UUID token, boolean isActive) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            return DataBase.getVacanciesWithActive(token, isActive);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void setActiveVacancy(UUID token, Integer number, boolean isActive) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.setActiveVacancy(token, number - 1, isActive);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void changeCompany(UUID token, String company) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.changeCompany(token, company);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void changeAddress(UUID token, String address) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.changeAddress(token, address);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public List<Employee> getEmployeesHaveAllSkillsDemandedLvl(UUID token, Integer numberVacancy) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            return DataBase.getEmployeesHaveAllSkillsDemandedLvl(token, numberVacancy - 1);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public List<Employee> getEmployeesHaveAllNecessarySkillsDemandedLvl(UUID token, Integer numberVacancy) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            return DataBase.getEmployeesHaveAllNecessarySkillsDemandedLvl(token, numberVacancy - 1);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public List<Employee> getEmployeesHaveAllSkills(UUID token, Integer numberVacancy) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            return DataBase.getEmployeesHaveAllSkills(token, numberVacancy - 1);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public List<Employee> getEmployeesWithOneSuitableSkillDemandedLvl(UUID token, Integer numberVacancy) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            return DataBase.getEmployeesWithOneSuitableSkillDemandedLvl(token, numberVacancy - 1);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

}
