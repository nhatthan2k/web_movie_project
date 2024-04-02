package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.webmovieapp.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
