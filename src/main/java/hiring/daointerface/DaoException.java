package hiring.daointerface;

public class DaoException extends Exception{
    private DaoErrorCode daoErrorCode;

    DaoException(DaoErrorCode daoErrorCode){
        super(daoErrorCode.getErrorString());
        this.daoErrorCode = daoErrorCode;
    }
    DaoException(DaoErrorCode daoErrorCode,Throwable cause){
        super(daoErrorCode.getErrorString(),cause);
        this.daoErrorCode = daoErrorCode;
    }
    public DaoErrorCode getDaoErrorCode(){
        return daoErrorCode;
    }

}
