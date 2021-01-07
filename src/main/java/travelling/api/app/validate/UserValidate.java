package travelling.api.app.validate;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import travelling.api.app.exception.ValidatorException;
import travelling.api.app.model.request.user.UserAuthRequest;
import travelling.api.app.model.request.user.UserChangePasswordRequest;


@Component
public class UserValidate {

    public static boolean validateUserName(String userName) {
        return StringUtils.isBlank(userName);
    }

    public static boolean validatePassword(String password) {
        return StringUtils.isBlank(password) && password.length() < 6;
    }

    public UserAuthRequest validateUserAuth(UserAuthRequest userAuthRequest) {
        return Validator
                .of(userAuthRequest)
                .validate(UserAuthRequest::getUserName, UserValidate::validateUserName, () -> new ValidatorException("userName not empty"))
                .validate(UserAuthRequest::getPassword, UserValidate::validatePassword, () -> new ValidatorException("password not empty"))
                .get();
    }

    public UserChangePasswordRequest validateChangePassword(UserChangePasswordRequest userChangePasswordRequest) {
        return Validator
                .of(userChangePasswordRequest)
                .validate(UserChangePasswordRequest::getCurrentPassword, UserValidate::validatePassword, () -> new ValidatorException("currentPassword not empty"))
                .validate(UserChangePasswordRequest::getNewPassword, UserValidate::validatePassword, () -> new ValidatorException("newPassword not empty"))
                .get();
    }
}
