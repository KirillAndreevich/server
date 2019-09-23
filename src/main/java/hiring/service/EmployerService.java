package hiring.service;

import com.google.gson.Gson;
import hiring.converter.Convert;
import hiring.daointerface.DaoException;
import hiring.daointerface.EmployerDaoImpl;
import hiring.database.DataBaseException;
import hiring.dto.DTO;
import hiring.dto.ErrorDto;
import hiring.dto.RegisterEmployerDtoRequest;
import hiring.model.Demand;


abstract public class EmployerService extends UserService {
    private static EmployerDaoImpl employerDao = new EmployerDaoImpl();

    public static String registerEmployer(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        RegisterEmployerDtoRequest registerEmployerDtoRequest = gson.fromJson(requestJsonString, RegisterEmployerDtoRequest.class);

        if (registerEmployerDtoRequest.getFirstName() == null || registerEmployerDtoRequest.getFirstName().equals("")) {
            errorDto.setError("Wrong first name");
            return gson.toJson(errorDto);
        }
        if (registerEmployerDtoRequest.getLastName().equals("") || registerEmployerDtoRequest.getLastName() == null) {
            errorDto.setError("Wrong last name");
            return gson.toJson(errorDto);
        }
        if (registerEmployerDtoRequest.getPatronymic() == null) registerEmployerDtoRequest.setPatronymic("-");

        if (registerEmployerDtoRequest.getEmail() == null || registerEmployerDtoRequest.getEmail().equals("")
                || !registerEmployerDtoRequest.getEmail().contains("@")) {
            errorDto.setError("Wrong email");
            return gson.toJson(errorDto);
        }
        if (registerEmployerDtoRequest.getLogin() == null || registerEmployerDtoRequest.getLogin().equals("")) {
            errorDto.setError("Wrong login");
            return gson.toJson(errorDto);
        }
        if (registerEmployerDtoRequest.getPassword().length() < 8 || registerEmployerDtoRequest.getPassword().equals("")) {
            errorDto.setError("The password must contain more 8 characters");
            return gson.toJson(errorDto);
        }
        if (registerEmployerDtoRequest.getAddress() == null || registerEmployerDtoRequest.getAddress().equals("")) {
            errorDto.setError("Wrong address");
            return gson.toJson(errorDto);
        }
        if (registerEmployerDtoRequest.getCompany() == null || registerEmployerDtoRequest.getCompany().equals("")) {
            errorDto.setError("Wrong company");
            return gson.toJson(errorDto);
        }
        try {
            return employerDao.insert(Convert.from(registerEmployerDtoRequest)).toString();
        } catch (DataBaseException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
    }

    public static String changeFirstName(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto = gson.fromJson(requestJsonString, DTO.class);
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getFirstName() == null || dto.getFirstName().equals("")) {
            errorDto.setError("Wrong first name");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.changeFirstName(dto.getToken(), dto.getFirstName());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String changeLastName(String requestJsonString) { //С этого начать
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getLastName() == null || dto.getLastName().equals("")) {
            errorDto.setError("Wrong last name");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.changeLastName(dto.getToken(), dto.getLastName());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String changePatronymic(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getPatronymic() == null || dto.getPatronymic().equals("")) {
            errorDto.setError("Wrong patronymic");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.changePatronymic(dto.getToken(), dto.getPatronymic());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String changeCompany(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getCompany() == null || dto.getCompany().equals("")) {
            errorDto.setError("Wrong company");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.changeCompany(dto.getToken(), dto.getCompany());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String changeAddress(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getAddress() == null || dto.getAddress().equals("")) {
            errorDto.setError("Wrong address");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.changeAddress(dto.getToken(), dto.getAddress());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String addVacany(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getVacancy() == null) {
            errorDto.setError("Vacancy is null");
            return gson.toJson(errorDto);
        }
        if (dto.getVacancy().getPosition() == null || dto.getVacancy().getPosition().equals("")) {
            errorDto.setError("Wrong vacancy position");
            return gson.toJson(errorDto);
        }
        if (dto.getVacancy().getSalary() == null) {
            errorDto.setError("Vacancy salary is null");
            return gson.toJson(errorDto);
        }
        int i = 1;
        for (Demand demand : dto.getVacancy().getDemands()) {
            if (demand.getName() == null || demand.getName().equals("")) {
                errorDto.setError(String.format("Vacancy demand %d wrong name", i));
                return gson.toJson(errorDto);
            }
            if (demand.getLvl() < 1 || demand.getLvl() > 5) {
                errorDto.setError(String.format("Vacancy demand %d wrong lvl", i));
                return gson.toJson(errorDto);
            }
            i++;
        }
        try {
            employerDao.insert(dto.getToken(), dto.getVacancy());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String changeVacancyPosition(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberVacancy() == null) {
            errorDto.setError("Vacancy number is null");
            return gson.toJson(errorDto);
        }
        if (dto.getPosition() == null || dto.getPosition().equals("")) {
            errorDto.setError("Wrong vacancy position");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.changeVacancyPosition(dto.getToken(), dto.getNumberVacancy(), dto.getPosition());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String changeVacancySalary(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberVacancy() == null) {
            errorDto.setError("Vacancy number is null");
            return gson.toJson(errorDto);
        }
        if (dto.getSalary() == null) {
            errorDto.setError("Vacancy salary is null");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.changeVacancySalary(dto.getToken(), dto.getNumberVacancy(), dto.getSalary());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String addVacancyDemands(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberVacancy() == null) {
            errorDto.setError("Vacancy number is null");
            return gson.toJson(errorDto);
        }
        int i = 1;
        for (Demand demand : dto.getDemands()) {
            if (demand.getName() == null || demand.getName().equals("")) {
                errorDto.setError(String.format("Vacancy demand %d wrong name", i));
                return gson.toJson(errorDto);
            }
            if (demand.getLvl() < 1 || demand.getLvl() > 5) {
                errorDto.setError(String.format("Vacancy demand %d wrong lvl", i));
                return gson.toJson(errorDto);
            }
            i++;
        }
        try {
            employerDao.addVacancyDemands(dto.getToken(), dto.getNumberVacancy(), dto.getDemands());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String deleteVacancyDemand(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberVacancy() == null) {
            errorDto.setError("Vacancy number is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberDemand() == null) {
            errorDto.setError("Vacancy number demand is null");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.deleteVacancyDemand(dto.getToken(), dto.getNumberVacancy(), dto.getNumberDemand());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String changeVacancyDemandName(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberVacancy() == null) {
            errorDto.setError("Vacancy number is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberDemand() == null) {
            errorDto.setError("Vacancy number demand is null");
            return gson.toJson(errorDto);
        }
        if (dto.getName() == null || dto.getName().equals("")) {
            errorDto.setError("Wrong demand name");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.changeVacancyDemandName(dto.getToken(), dto.getNumberVacancy(), dto.getNumberDemand(), dto.getName());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String changeVacancyDemandLvl(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberVacancy() == null) {
            errorDto.setError("Vacancy number is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberDemand() == null) {
            errorDto.setError("Vacancy number demand is null");
            return gson.toJson(errorDto);
        }
        if (dto.getLvl() == null || dto.getLvl() < 1 || dto.getLvl() > 5) {
            errorDto.setError("Wrong lvl demand");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.changeVacancyDemandLvl(dto.getToken(), dto.getNumberVacancy(), dto.getNumberDemand(), dto.getLvl());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String changeVacancyDemandNecessarily(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberVacancy() == null) {
            errorDto.setError("Vacancy number is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberDemand() == null) {
            errorDto.setError("Vacancy number demand is null");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.changeVacancyDemandNecessarily(dto.getToken(), dto.getNumberVacancy(), dto.getNumberDemand(), dto.isNecessarily());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String getVacancies(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        try {
            return gson.toJson(employerDao.getVacancies(dto.getToken()));
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
    }

    public static String getVacanciesWithActive(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        try {
            return gson.toJson(employerDao.getVacanciesWithActive(dto.getToken(), dto.isActive()));
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
    }

    public static String setActiveVacancy(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberVacancy() == null) {
            errorDto.setError("Vacancy number is null");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.setActiveVacancy(dto.getToken(), dto.getNumberVacancy(), dto.isActive());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String deleteVacancy(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberVacancy() == null) {
            errorDto.setError("Vacancy number is null");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.deleteVacancy(dto.getToken(), dto.getNumberVacancy());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }


    public static String getEmployeesHaveAllSkillsDemandedLvl(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberVacancy() == null) {
            errorDto.setError("Vacancy number is null");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.getEmployeesHaveAllSkillsDemandedLvl(dto.getToken(), dto.getNumberVacancy());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String getEmployeesHaveAllNecessarySkillsDemandedLvl(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberVacancy() == null) {
            errorDto.setError("Vacancy number is null");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.getEmployeesHaveAllNecessarySkillsDemandedLvl(dto.getToken(), dto.getNumberVacancy());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String getEmployeesHaveAllSkills(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberVacancy() == null) {
            errorDto.setError("Vacancy number is null");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.getEmployeesHaveAllSkills(dto.getToken(), dto.getNumberVacancy());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String getEmployeesWithOneSuitableSkillDemandedLvl(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberVacancy() == null) {
            errorDto.setError("Vacancy number is null");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.getEmployeesWithOneSuitableSkillDemandedLvl(dto.getToken(), dto.getNumberVacancy());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String recruit(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getNumberVacancy() == null) {
            errorDto.setError("Vacancy number is null");
            return gson.toJson(errorDto);
        }
        if (dto.getLogin().equals("") || dto.getLogin() == null) {
            errorDto.setError("Wrong login format");
            return gson.toJson(errorDto);
        }
        try {
            employerDao.recruit(dto.getToken(), dto.getNumberVacancy(),dto.getLogin());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }
    public static String getEmployer(String requestJsonString){
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        try {
            return gson.toJson(employerDao.getEmployer(dto.getToken()));
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
    }

}
