package hiring.daointerface;

import hiring.database.DataBaseException;
import hiring.model.Employee;
import hiring.model.Skill;
import hiring.model.Vacancy;

import java.util.List;
import java.util.UUID;

public interface EmployeeDao {
    UUID insert(Employee employee) throws DataBaseException;

    Employee getEmployee(UUID token) throws DataBaseException, DaoException;

    void changeFirstName(UUID token, String firstName) throws DataBaseException, DaoException;

    void changeLastName(UUID token, String lastName) throws DataBaseException, DaoException;

    void changePatronymic(UUID token, String patronymic) throws DataBaseException, DaoException;

    void insert(UUID token, Skill skill) throws DataBaseException, DaoException;

    void deleteSkill(UUID token, Integer number) throws DataBaseException, DaoException;

    //void addSkills(UUID token, List<Skill> skills)throws DataBaseException,DaoException;

    void changeSkillName(UUID token, Integer index, String name) throws DataBaseException, DaoException;

    void changeSkillLvl(UUID token, Integer index, Integer lvl) throws DataBaseException, DaoException;

    void setActiveEmployee(UUID token, boolean isActive) throws DataBaseException, DaoException;

    List<Vacancy> getSuitableVacanciesAllDemandsMyLvl(UUID token) throws DataBaseException, DaoException;

    List<Vacancy> getSuitableVacanciesNecessaryDemandsMyLvl(UUID token) throws DataBaseException, DaoException;

    List<Vacancy> getSuitableVacanciesAllDemandsAllLvl(UUID token) throws DataBaseException, DaoException;
/*
    List<Vacancy> getVacanciesWithOneSuitableDemandsMyLvl(UUID token)throws DataBaseException,DaoException;
*/
}
