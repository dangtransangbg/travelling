package travelling.api.app.service;


import travelling.api.app.model.request.user.UserAuthRequest;
import travelling.api.app.model.request.user.UserChangePasswordRequest;
import travelling.api.app.model.request.user.UserRequest;
import travelling.api.app.model.request.user.UserSaveRequest;
import travelling.api.app.model.response.ListResponse;
import travelling.api.app.model.response.user.UserResponse;
import travelling.api.security.jwt.model.JwtPayload;

import java.util.List;

public interface UserService {
    void save(UserSaveRequest userSaveRequest);

    String auth(UserAuthRequest userAuthRequest);

    List<UserResponse> getAll();

    ListResponse<UserResponse> findAll(UserRequest userRequest);

    UserResponse getInfo();

    UserResponse get(Long id);

    void changePassword(UserChangePasswordRequest changePassword);

    JwtPayload parseToken();
}
