package travelling.api.app.exception;

public class ObjectNotFoundException extends RuntimeException {
    private String description;

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, String description) {
        super(message);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
