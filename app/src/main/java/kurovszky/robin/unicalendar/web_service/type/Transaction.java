package kurovszky.robin.unicalendar.web_service.type;

public enum Transaction {
    REGISTER(true),
    LOGIN(false),
    ADD_INSTITUTE(true),
    GET_SUBJECTS(false),
    ADD_SUBJECTS(false),
    COMMENT(false),



    ;
    boolean allowedWithoutLogin;

    Transaction(boolean allowedWithoutLogin) {
        this.allowedWithoutLogin = allowedWithoutLogin;
    }

    public boolean isAllowedWithoutLogin() {
        return allowedWithoutLogin;
    }
}
