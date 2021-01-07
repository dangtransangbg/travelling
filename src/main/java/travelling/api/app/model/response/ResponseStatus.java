package travelling.api.app.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatus implements Serializable {

    private static final long serialVersionUID = 7143918718522374545L;
    private String code;
    private String message;
}
