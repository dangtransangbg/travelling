package travelling.api.app.service.impl;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import travelling.api.app.common.constant.MessageConstant;
import travelling.api.app.entity.Role;
import travelling.api.app.entity.User;
import travelling.api.app.exception.InternalServerException;
import travelling.api.app.exception.LoginException;
import travelling.api.app.exception.ObjectNotFoundException;
import travelling.api.app.exception.PasswordException;
import travelling.api.app.mapper.UserMapper;
import travelling.api.app.model.request.user.UserAuthRequest;
import travelling.api.app.model.request.user.UserChangePasswordRequest;
import travelling.api.app.model.request.user.UserRequest;
import travelling.api.app.model.request.user.UserSaveRequest;
import travelling.api.app.model.response.ListResponse;
import travelling.api.app.model.response.user.UserResponse;
import travelling.api.app.repository.UserRepository;
import travelling.api.app.repository.specification.UserSpecification;
import travelling.api.app.service.UserService;
import travelling.api.app.util.PasswordHasher;
import travelling.api.security.UserDetails;
import travelling.api.security.jwt.TokenProducer;
import travelling.api.security.jwt.model.JwtPayload;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static travelling.api.app.util.ArrayUtils.convertToString;

@Service
@Data
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final TokenProducer tokenProducer;
    private final UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = InternalServerException.class)
    public void save(UserSaveRequest userSaveRequest) {
        User user = userMapper.convertToEntity(userSaveRequest);
        userRepository.save(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public String auth(UserAuthRequest userAuthRequest) {
        try {
            String password = PasswordHasher.hash(userAuthRequest.getPassword());
            userAuthRequest.setPassword(password);
            Optional<User> user = userRepository.findOne(UserSpecification.filterAuth(userAuthRequest));
            if (!user.isPresent()) {
                throw new LoginException(MessageConstant.LOGIN_FALSE.value());
            }
            JwtPayload payload = createJwtPayload(user.get());
            String token = tokenProducer.token(payload);

            return token;
        } catch (NoSuchAlgorithmException e) {
            throw new InternalServerException(MessageConstant.INTERNAL_SERVER.value());
        }

    }

    @Override
    public List<UserResponse> getAll() {
        return userMapper.toUsersResponse(userRepository.findAll());
    }

    @Override
    public ListResponse<UserResponse> findAll(UserRequest userRequest) {
        ListResponse<UserResponse> userResponse = null;
        List<User> users = (List<User>) userRepository.findAll(PageRequest.of(userRequest.getPage(),userRequest.getSize()));
        long count = userRepository.count();
        int total = (int) Math.ceil((double) count/userRequest.getSize());
//        userResponse.setCurrentPage(userRequest.getPage());
//        userResponse.setTotalPage(total);
        userResponse.setData(userMapper.toUsersResponse(users));
        return userResponse;
    }


    @Override
    public UserResponse getInfo() {
        Optional<User> user = userRepository.findById(parseToken().getId());
        if (!user.isPresent()) {
            throw new ObjectNotFoundException(MessageConstant.USER_NOT_FOUND.value());
        }
        return userMapper.convertToResponse(user.get());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public UserResponse get(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new ObjectNotFoundException(MessageConstant.USER_NOT_FOUND.value());
        }
        return userMapper.convertToResponse(user.get());
    }

    @Override
    @Transactional(rollbackFor = InternalServerException.class)
    public void changePassword(UserChangePasswordRequest changePassword) {
        try {
            Optional<User> user = userRepository.findById((parseToken().getId()));
            if (!user.isPresent()) {
                throw new ObjectNotFoundException(MessageConstant.USER_NOT_FOUND.value());
            }
            String currentPassword = PasswordHasher.hash(user.get().getPassword());
            if (!currentPassword.equals(user.get().getPassword())) {
                throw new PasswordException("Invalid current password");
            }
            String password = PasswordHasher.hash(changePassword.getNewPassword());
            user.get().setPassword(password);
            userRepository.save(user.get());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

    public JwtPayload parseToken() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getUser();
    }

    private JwtPayload createJwtPayload(User user) {
        JwtPayload payload = new JwtPayload();
        payload.setId(user.getId());
        payload.setUserName(user.getUserName());
        payload.setEmail(user.getEmail());

        StringBuilder roleString = new StringBuilder();
        Set<Role> roles = user.getRoles();

        String role = convertToString(roles.stream().map(Role::getRoleName).collect(Collectors.toList()));

        payload.setRole(role);
        return payload;
    }

}
