package exception;

public class ApiException extends Exception {
    private final String message;
    private final int errorCode;

    public ApiException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        this(message, 500);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
