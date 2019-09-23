package hiring.daointerface;

import hiring.database.DataBase;
import hiring.database.DataBaseException;
import hiring.model.Employee;
import hiring.model.Skill;
import hiring.model.Vacancy;

import java.util.List;
import java.util.UUID;

public class EmployeeDaoImpl implements EmployeeDao {
    public EmployeeDaoImpl(){

    }
    public Employee getEmployee(UUID token) throws DataBaseException, DaoException{
        if (DataBase.UserIsOnline(token)) {
            return (Employee) DataBase.getUser(token);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }
    public UUID insert(Employee employee) throws DataBaseException {
        return DataBase.insert(employee);
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

    public void insert(UUID token, Skill skill) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.insert(token, skill);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void deleteSkill(UUID token, Integer number) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.deleteSkill(token, number - 1);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }
/*
    public void addSkills(UUID token, List<Skill> skills) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.addSkills(token, skills);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }
*/
    public void changeSkillName(UUID token, Integer number, String name) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.changeSkillName(token, number - 1, name);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void changeSkillLvl(UUID token, Integer number, Integer lvl) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.changeSkillLvl(token, number - 1, lvl);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void setActiveEmployee(UUID token, boolean isActive) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.setActiveEmployee(token, isActive);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public List<Vacancy> getSuitableVacanciesAllDemandsMyLvl(UUID token) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            return DataBase.getSuitableVacanciesAllDemandsMyLvl(token);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public List<Vacancy> getSuitableVacanciesNecessaryDemandsMyLvl(UUID token) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            return DataBase.getSuitableVacanciesNecessaryDemandsMyLvl(token);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public List<Vacancy> getSuitableVacanciesAllDemandsAllLvl(UUID token) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            return DataBase.getSuitableVacanciesAllDemandsAllLvl(token);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public List<Vacancy> getVacanciesWithOneSuitableDemandsMyLvl(UUID token) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            return DataBase.getVacanciesWithOneSuitableDemandsMyLvl(token);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

}
