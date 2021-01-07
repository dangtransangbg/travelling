package travelling.api.app.exception;

public class TimeException extends RuntimeException {

    private String message;

    public TimeException(String message) {
        super(message);
    }
}
