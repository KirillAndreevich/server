package hiring.service;

import com.google.gson.Gson;
import hiring.converter.Convert;
import hiring.daointerface.DaoException;
import hiring.daointerface.EmployeeDaoImpl;
import hiring.database.DataBaseException;
import hiring.dto.DTO;
import hiring.dto.ErrorDto;
import hiring.dto.RegisterEmployeeDtoRequest;

abstract public class EmployeeService extends UserService {
    private static EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();


    public static String registerEmployee(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        RegisterEmployeeDtoRequest registerEmployeeDtoRequest = gson.fromJson(requestJsonString, RegisterEmployeeDtoRequest.class);

        if (registerEmployeeDtoRequest.getFirstName() == null || registerEmployeeDtoRequest.getFirstName().equals("")) {
            errorDto.setError("Wrong first name");
            return gson.toJson(errorDto);
        }
        if (registerEmployeeDtoRequest.getLastName().equals("") || registerEmployeeDtoRequest.getLastName() == null) {
            errorDto.setError("Wrong last name");
            return gson.toJson(errorDto);
        }
        if (registerEmployeeDtoRequest.getPatronymic() == null) registerEmployeeDtoRequest.setPatronymic("-");

        if (registerEmployeeDtoRequest.getEmail() == null || registerEmployeeDtoRequest.getEmail().equals("")
                || !registerEmployeeDtoRequest.getEmail().contains("@")) {
            errorDto.setError("Wrong email");
            return gson.toJson(errorDto);
        }
        if (registerEmployeeDtoRequest.getLogin() == null || registerEmployeeDtoRequest.getLogin().equals("")) {
            errorDto.setError("Wrong login");
            return gson.toJson(errorDto);
        }
        if (registerEmployeeDtoRequest.getPassword().length() < 8 || registerEmployeeDtoRequest.getPassword() == null) {
            errorDto.setError("The password must contain more 8 characters");
            return gson.toJson(errorDto);
        }
        try {
            return employeeDao.insert(Convert.from(registerEmployeeDtoRequest)).toString();
        } catch (DataBaseException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
    }

    /*
        public static String addSkills(String requestJsonString) {
            Map<String, String> map = new HashMap<>();
            Gson gson = new Gson();
            DTO dto = gson.fromJson(requestJsonString, DTO.class);
            for (Skill skill : dto.getSkillss()) {
                if (skill.getName().equals("") || skill.getName() == null) {
                    map.put("error", "Wrong skill name");
                    return map.toString();
                }
                if (skill.getLvl() > 5 || skill.getLvl() < 1 || skill.getLvl() == null) {
                    map.put("error", "Wrong lvl value");
                    return map.toString();
                }
                try {
                    EmployeeService.employeeDao.addSkills(dto.getToken(), dto.getSkillss());
                } catch (DaoException | DataBaseException e) {
                    Gson gson1 = new Gson();
                    map.put("error", e.getMessage());
                    gson1.toJson(map);
                    return gson1.toString();
                }
            }
            return "";
        }
    */
    public static String deleteSkill(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e){
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
            if (dto.getToken() == null ) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        try {

            employeeDao.deleteSkill(dto.getToken(), dto.getNumber());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "{}";
    }

    public static String changeFirstName(String requestJsonString) {
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
        if (dto.getFirstName() == null || dto.getFirstName().equals("")) {
            errorDto.setError("Wrong first name");
            return gson.toJson(errorDto);
        }
        try {
            employeeDao.changeFirstName(dto.getToken(), dto.getFirstName());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "{}";
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
            employeeDao.changeLastName(dto.getToken(), dto.getLastName());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "{}";
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
            employeeDao.changePatronymic(dto.getToken(), dto.getPatronymic());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "{}";
    }

    public static String getSuitableVacanciesAllDemandsMyLvl(String requestJsonString) {
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
            return gson.toJson(employeeDao.getSuitableVacanciesAllDemandsMyLvl(dto.getToken()));
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }

    }

    public static String getSuitableVacanciesNecessaryDemandsMyLvl(String requestJsonString) {
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
            return gson.toJson(employeeDao.getSuitableVacanciesNecessaryDemandsMyLvl(dto.getToken()));
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
    }

    public static String getSuitableVacanciesAllDemandsAllLvl(String requestJsonString) {
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
            return gson.toJson(employeeDao.getSuitableVacanciesAllDemandsAllLvl(dto.getToken()));
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
    }

    public static String getVacanciesWithOneSuitableDemandsMyLvl(String requestJsonString) {
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
            return gson.toJson(employeeDao.getVacanciesWithOneSuitableDemandsMyLvl(dto.getToken()));
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
    }

    public static String setActiveEmployee(String requestJsonString) {
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
            employeeDao.setActiveEmployee(dto.getToken(), dto.isActive());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "{}";
    }

    public static String changeSkillLvl(String requestJsonString) {
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
        if (dto.getLvl() < 1 || dto.getLvl() > 5) {
            errorDto.setError("Wrong skill lvl");
            return gson.toJson(errorDto);
        }
        if (dto.getNumber() == null) {
            errorDto.setError("Skill number is null");
            return gson.toJson(errorDto);
        }
        try {
            employeeDao.changeSkillLvl(dto.getToken(), dto.getNumber(), dto.getLvl());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "{}";

    }

    public static String changeSkillName(String requestJsonString) {
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
        if (dto.getName() == null || dto.getName().equals("")) {
            errorDto.setError("Wrong skill name");
            return gson.toJson(errorDto);
        }
        if (dto.getNumber() == null) {
            errorDto.setError("Skill number is null");
            return gson.toJson(errorDto);
        }
        try {
            employeeDao.changeSkillName(dto.getToken(), dto.getNumber(), dto.getName());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "{}";

    }

    public static String addSkill(String requestJsonString) { //ПРОБЛЕМА!!! Будет ли распознано умение...
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
        if (dto.getSkill() == null) {
            errorDto.setError("Skill is null");
            return gson.toJson(errorDto);
        }
        if (dto.getSkill().getName() == null || dto.getSkill().getName().equals("")) {
            errorDto.setError("Wrong skill name");
            return gson.toJson(errorDto);
        }
        if (dto.getSkill().getLvl() == null) {
            errorDto.setError("Skill lvl is null");
            return gson.toJson(errorDto);
        }
        try {
            employeeDao.insert(dto.getToken(), dto.getSkill());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "{}";
    }
    public static String getEmployee(String requestJsonString){
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
            return gson.toJson(Convert.from(employeeDao.getEmployee(dto.getToken())));
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
    }

}
