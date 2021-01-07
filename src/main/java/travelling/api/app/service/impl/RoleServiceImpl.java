package travelling.api.app.service.impl;


import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import travelling.api.app.entity.Role;
import travelling.api.app.repository.RoleRepository;
import travelling.api.app.service.RoleService;

import java.util.List;

@Service
@Data
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
