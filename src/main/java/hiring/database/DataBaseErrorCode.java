package hiring.database;

public enum DataBaseErrorCode {
    WRONG_TOKEN("Wrong token"), MATCHING_LOGIN("This login is busy"), WRONG_LOGIN_OR_PASSWORD("Wrong login or password"),
    VACANCY_NOT_FOUND("Vacancy not found"), SKILL_NOT_FOUND("Skill not found"), DEMAND_NOT_FOUND("Demand not found"),
    ERROR_SAVE_DATA_BASE("Error saving data base"), ERROR_LOAD_DATA_BASE("Error loading data base"),
    DUPLICATE_SKILL("Duplicate skill"),DUPLICATE_DEMAND("Duplicate demand"),INVALID_LOGIN("Invalid employee login");

    private String errorString;

    DataBaseErrorCode(String errorCode) {
        this.errorString = errorCode;
    }

    public String getErrorString() {
        return errorString;
    }
}
