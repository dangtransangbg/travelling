package travelling.api.app.common.role;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static travelling.api.app.common.role.RoleConstant.*;

@Target(METHOD)
@Retention(RUNTIME)
@PreAuthorize(ROLE_ROOT_ADMIN + OR + ROLE_ADMIN)
public @interface ADMIN {
}
