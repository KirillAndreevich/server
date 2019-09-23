package hiring.database;

public class DataBaseException extends Exception{
    private DataBaseErrorCode dataBaseErrorCode;

    DataBaseException(DataBaseErrorCode dataBaseErrorCode){
        super(dataBaseErrorCode.getErrorString());
        this.dataBaseErrorCode = dataBaseErrorCode;
    }
    DataBaseException(DataBaseErrorCode dataBaseErrorCode,Throwable cause){
        super(dataBaseErrorCode.getErrorString(),cause);
        this.dataBaseErrorCode = dataBaseErrorCode;
    }

    public DataBaseErrorCode getDataBaseErrorCode() {
        return dataBaseErrorCode;
    }
}
