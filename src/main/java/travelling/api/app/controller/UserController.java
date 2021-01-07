package travelling.api.app.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelling.api.app.common.role.ADMIN;
import travelling.api.app.common.role.USER;
import travelling.api.app.controller.BaseController;
import travelling.api.app.model.request.user.UserAuthRequest;
import travelling.api.app.model.request.user.UserChangePasswordRequest;
import travelling.api.app.model.request.user.UserSaveRequest;
import travelling.api.app.model.response.ObjectResponse;
import travelling.api.app.service.UserService;
import travelling.api.app.validate.UserValidate;

@RestController
@Data
public class UserController extends BaseController {

    private final UserService userService;
    private final UserValidate userValidate;


    @PostMapping("/user")
    @ADMIN
    public ResponseEntity<ObjectResponse> save(@RequestBody UserSaveRequest userSaveRequest) {
        return success(userSaveRequest, userService::save);
    }

    @PostMapping("/admin/auth")
    public ResponseEntity<ObjectResponse> auth(@RequestBody UserAuthRequest userAuthRequest) {
        userAuthRequest = userValidate.validateUserAuth(userAuthRequest);

        String sondx = "sondx";

        return success(userAuthRequest, userService::auth);
    }

    @GetMapping("/info")
    @USER
    public ResponseEntity<ObjectResponse> getInfo() {
        return success(userService::getInfo);
    }

    @GetMapping("/user/{id}")
    @ADMIN
    public ResponseEntity<ObjectResponse> getById(@PathVariable long id) {
        return success(id, userService::get);
    }

    @PatchMapping("/user/password")
    @USER
    public ResponseEntity<ObjectResponse> changePassword(@RequestBody UserChangePasswordRequest changePasswordRequest) {
        changePasswordRequest = userValidate.validateChangePassword(changePasswordRequest);

        return success(changePasswordRequest, userService::changePassword);
    }
}
