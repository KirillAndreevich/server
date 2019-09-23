package hiring.daointerface;

public enum DaoErrorCode {
    USER_OFFLINE("User offline");
    private String errorString;

    DaoErrorCode(String errorCode) {
        this.errorString = errorCode;
    }

    public String getErrorString() {
        return errorString;
    }
}
