package hiring.daointerface;

import hiring.database.DataBase;
import hiring.database.DataBaseException;

import java.io.File;

public class DataBaseDaoImpl implements DataBaseDao{
    public DataBaseDaoImpl(){

    }
    public void startDataBase(){
        DataBase.startDataBase();
    }

    public void saveDataBase(File file) throws DataBaseException{
        DataBase.saveDataBase(file);
    }

    public void loadDataBase(File file) throws DataBaseException{
        DataBase.loadDataBase(file);
    }
}
