package travelling.api.app.model.request.user;

import lombok.Data;

@Data
public class UserChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
}
