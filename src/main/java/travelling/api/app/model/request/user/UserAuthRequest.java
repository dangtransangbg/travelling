package travelling.api.app.model.request.user;

import lombok.Data;


@Data
public class UserAuthRequest {
    private String userName;
    private String password;
}
