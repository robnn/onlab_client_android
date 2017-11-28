package kurovszky.robin.unicalendar.exception;

import kurovszky.robin.unicalendar.web_service.error.ErrorObject;

public class BaseException extends Exception {

    private ErrorObject errorObject;

    public BaseException(String message) {
        super(message);
    }

    /**
     * Exceptions created with this constructor must be handled with a new ErrorObject created in the catch block, with the application context
     */
    public BaseException(ErrorObject errorObject) {
        this.errorObject = errorObject;
    }

    public ErrorObject getErrorObject() {
        return errorObject;
    }
}
