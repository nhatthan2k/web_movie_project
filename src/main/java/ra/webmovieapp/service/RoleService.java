package ra.webmovieapp.service;

import ra.webmovieapp.model.entity.Role;
import ra.webmovieapp.model.enums.ERoleName;

public interface RoleService {
    Role findByRoleName(ERoleName roleName);
}
