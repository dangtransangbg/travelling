package travelling.api.app.model.request;

import lombok.Data;

@Data
public class SendEmailRequest {
    private String to;
    private String subject;
    private String content;
}
