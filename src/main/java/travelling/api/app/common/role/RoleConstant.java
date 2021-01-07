package travelling.api.app.common.role;

public interface RoleConstant {
    String ROLE_USER = "hasAuthority('USER')";
    String ROLE_ADMIN = "hasAuthority('ADMIN')";
    String ROLE_ROOT_ADMIN = "hasAuthority('ROOT_ADMIN')";
    String OR = " or ";

    String ROOT_ADMIN = "ROOT_ADMIN";
    String ADMIN = "ADMIN";
    String USER = "USER";

}
