package travelling.api.app.model.request.user;

import lombok.Data;

import java.util.Set;

@Data
public class UserSaveRequest {
    private String userName;
    private String password;
    private String phone;
    private String name;
    private Set<Long> roleId;
}
