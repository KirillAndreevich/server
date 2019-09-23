package hiring.daointerface;

import hiring.database.DataBaseException;

import java.io.File;

public interface DataBaseDao {
    void startDataBase();

    void saveDataBase(File file) throws DataBaseException;

    void loadDataBase(File file) throws DataBaseException;
}
