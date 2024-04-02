package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.webmovieapp.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
