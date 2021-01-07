package travelling.api.app.model.request.user;

import lombok.Data;

@Data
public class UserResetPasswordRequest {
    private String email;
}
