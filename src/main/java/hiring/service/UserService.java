package hiring.service;

import com.google.gson.Gson;
import hiring.daointerface.DaoException;
import hiring.daointerface.UserDaoImpl;
import hiring.database.DataBaseException;
import hiring.dto.DTO;
import hiring.dto.ErrorDto;

abstract public class UserService {
    private static UserDaoImpl userDao = new UserDaoImpl();

    public static String deleteUser(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto = gson.fromJson(requestJsonString, DTO.class);
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        try {
            userDao.deleteUser(dto.getToken());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "{}";

    }

    //boolean UserIsOnline(UUID token);

    public static String loginUser(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto = gson.fromJson(requestJsonString, DTO.class);
        if (dto.getLogin() == null || dto.getLogin().equals("")) {
            errorDto.setError("Wrong login format");
            return gson.toJson(errorDto);
        }
        if (dto.getPassword() == null || dto.getPassword().equals("") || dto.getPassword().length()<8) {
            errorDto.setError("Wrong password");
            return gson.toJson(errorDto);
        }
        try {
            userDao.loginUser(dto.getLogin(), dto.getPassword());
        } catch (DataBaseException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "{}";
    }

    public static String exitUser(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto = gson.fromJson(requestJsonString, DTO.class);
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        try {
            userDao.exitUser(dto.getToken());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "{}";
    }

    public static String changeEmail(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto;
        try {

            dto = gson.fromJson(requestJsonString, DTO.class);//Может быть исключение, обработать!
        }catch (Exception e) {
            errorDto.setError("Request dto error");
            return gson.toJson(errorDto);
        }
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getEmail() == null || dto.getEmail().equals("")
                || !dto.getEmail().contains("@")) {
            errorDto.setError("Wrong email");
            return gson.toJson(errorDto);
        }
        try {
            userDao.changeEmail(dto.getToken(), dto.getEmail());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "{}";
    }

    public static String changePassword(String requestJsonString) {
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        DTO dto = gson.fromJson(requestJsonString, DTO.class);
        if (dto.getToken() == null) {
            errorDto.setError("Token is null");
            return gson.toJson(errorDto);
        }
        if (dto.getPassword().length() < 8 || dto.getPassword() == null) {
            errorDto.setError("The password must contain more 8 characters");
            return gson.toJson(errorDto);
        }
        try {
            userDao.changePassword(dto.getToken(), dto.getPassword());
        } catch (DataBaseException | DaoException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "{}";
    }

}
