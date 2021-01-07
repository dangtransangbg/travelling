package travelling.api.app.translate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import travelling.api.app.model.response.GeneralResponse;
import travelling.api.app.model.response.ResponseStatus;

@Component
@AllArgsConstructor
public class ResponseUtils {

    private final MessageTranslator messageTranslator;

    public GeneralResponse generalResponse(String code) {
        GeneralResponse response = new GeneralResponse();
        ResponseStatus responseStatus = new ResponseStatus();

        responseStatus.setCode(code);
        responseStatus.setMessage(messageTranslator.getMessage(code));

        response.setStatus(responseStatus);

        return response;
    }

    public GeneralResponse generalResponse(String code, String message) {
        GeneralResponse generalResponse = generalResponse(code);
        generalResponse.getStatus().setMessage(message);

        return generalResponse;
    }
}
