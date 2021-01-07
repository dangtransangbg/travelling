package travelling.api.app.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import travelling.api.app.common.role.ADMIN;
import travelling.api.app.controller.BaseController;
import travelling.api.app.model.response.ObjectResponse;
import travelling.api.app.service.RoleService;

@RestController
@Data
public class RoleController extends BaseController {

    private final RoleService roleService;

    @GetMapping("/roles")
    @ADMIN
    public ResponseEntity<ObjectResponse> getAll() {
        return success(roleService::getRoles);
    }
}
