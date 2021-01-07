package travelling.api.app.repository;

import travelling.api.app.entity.Role;

import java.util.Set;

public interface RoleRepository extends BaseRepository<Role, Long> {
    Set<Role> findByIdIn(Set<Long> ids);
}
