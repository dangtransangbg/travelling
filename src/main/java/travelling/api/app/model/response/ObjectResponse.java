package travelling.api.app.model.response;

import lombok.*;

@Getter
@Setter
@ToString
public class ObjectResponse  {
    private ResponseStatus status;
    private Object data;

    @Getter
    @Setter
    @ToString
    public static class ResponseStatus{
        private String code;
        private String message;
        private int statusCode;
    }
}
