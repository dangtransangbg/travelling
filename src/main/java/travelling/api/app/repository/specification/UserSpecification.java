package travelling.api.app.repository.specification;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import travelling.api.app.entity.User;
import travelling.api.app.model.request.user.UserAuthRequest;

public class UserSpecification {

    public static Specification<User> filterAuth(UserAuthRequest userAuthRequest) {
        return Specification.where(withUserName(userAuthRequest.getUserName()))
                .and(withPassword(userAuthRequest.getPassword()));
    }

    private static Specification<User> withUserName(String userName) {
        if (StringUtils.isBlank(userName)) return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("userName"), userName);
    }

    private static Specification<User> withPassword(String password) {
        if (StringUtils.isBlank(password)) return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("password"), password);
    }
}
