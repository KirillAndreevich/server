package hiring.service;

import com.google.gson.Gson;
import hiring.daointerface.DataBaseDaoImpl;
import hiring.database.DataBaseException;
import hiring.dto.ErrorDto;

import java.io.File;

abstract public class ServerService {
    private static DataBaseDaoImpl dataBaseDao = new DataBaseDaoImpl();
    public static String startDataBase(){
        dataBaseDao.startDataBase();
        return "";
    }

    public static String saveDataBase(String file){
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        if(file == null){
            errorDto.setError("Wrong file name");
            return gson.toJson(errorDto);
        }
        File file1 = new File(file);

        try {
            dataBaseDao.saveDataBase(file1);
        }catch (DataBaseException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }

    public static String loadDataBase(String file){
        ErrorDto errorDto = new ErrorDto();
        Gson gson = new Gson();
        if(file == null){
            errorDto.setError("Wrong file name");
            return gson.toJson(errorDto);
        }
            File file1 = new File(file);

        try {
            dataBaseDao.loadDataBase(file1);
        }catch (DataBaseException e) {
            errorDto.setError(e.getMessage());
            return gson.toJson(errorDto);
        }
        return "";
    }
}
