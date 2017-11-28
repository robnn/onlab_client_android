package kurovszky.robin.unicalendar.web_service.error;

import android.content.Context;

import kurovszky.robin.unicalendar.R;
import kurovszky.robin.unicalendar.web_service.type.ErrorCode;

public class ErrorObject {
    private boolean failed = true;
    private ErrorCode errorCode;
    private Context context;
    private String message;


    public ErrorObject(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorObject(ErrorCode errorCode, Context application) {
        if (errorCode == null)
            throw new RuntimeException("ErrorCode cannot be null");
        context = application;
        this.errorCode = errorCode;
        buildMessage();

    }

    public ErrorObject(Context application) {
        context = application;
        this.errorCode = ErrorCode.DEFAULT;
        buildMessage();
    }

    private void buildMessage() {
        switch (errorCode) {
            case LOGIN_FAILED:
                message = context.getResources().getString(R.string.auth_fail);
                break;
            case USERNAME_EXISTS:
                message = context.getResources().getString(R.string.register_fail_username_exists);
                break;
            case SERVER_DOWN:
                message = context.getResources().getString(R.string.server_fail);
                break;
            case INSTITUTE_NAME_EMPTY:
                message = context.getResources().getString(R.string.institute_name_cannot_be_empty);
                break;
            case CANNOT_FIND_SUBJECT_ON_SERVER:
                message = context.getResources().getString(R.string.subject_not_found_on_server);
                break;
            case ALL_FIELDS_MUST_BE_FILLED:
                message = context.getResources().getString(R.string.all_fields_must_be_filled);
                break;
            case NO_ERROR:
                message = context.getResources().getString(R.string.server_fail);
                failed = false;
                break;
            default:
                message = context.getResources().getString(R.string.default_fail_message);
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorObject that = (ErrorObject) o;

        return errorCode == that.errorCode;

    }

    @Override
    public int hashCode() {
        return errorCode.hashCode();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public boolean isFailed() {
        return failed;
    }
}
