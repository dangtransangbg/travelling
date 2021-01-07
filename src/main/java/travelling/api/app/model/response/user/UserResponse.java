package travelling.api.app.model.response.user;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String name;
    private String phone;
    private String address;
    private String sex;
}
