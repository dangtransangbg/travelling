package travelling.api.app.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import travelling.api.app.common.constant.MessageConstant;
import travelling.api.app.entity.Role;
import travelling.api.app.entity.User;
import travelling.api.app.exception.InternalServerException;
import travelling.api.app.model.request.user.UserSaveRequest;
import travelling.api.app.model.response.user.UserResponse;
import travelling.api.app.repository.RoleRepository;
import travelling.api.app.util.BeanUtils;
import travelling.api.app.util.PasswordHasher;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class UserMapper implements Mapper {

    private final RoleRepository roleRepository;

    public User convertToEntity(UserSaveRequest userSaveRequest) {
        try {
            User user = new User();
            BeanUtils.refine(userSaveRequest, user, BeanUtils::copyNonNull);
            String password = PasswordHasher.hash(userSaveRequest.getPassword());
            user.setPassword(password);
            Set<Role> roles = roleRepository.findByIdIn(userSaveRequest.getRoleId());
            user.setRoles(roles);

            return user;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

            throw new InternalServerException(MessageConstant.INTERNAL_SERVER.value());
        }

    }
    public List<UserResponse> toUsersResponse(List<User> users){
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users){
            userResponses.add(convertToResponse(user));
        }
        return userResponses;
    }

    public UserResponse convertToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.refine(user, userResponse, BeanUtils::copyNonNull);

        return userResponse;
    }
}
