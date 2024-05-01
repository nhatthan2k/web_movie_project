package ra.webmovieapp.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webmovieapp.model.entity.Role;
import ra.webmovieapp.model.enums.ERoleName;
import ra.webmovieapp.repository.RoleRepository;
import ra.webmovieapp.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByRoleName(ERoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
