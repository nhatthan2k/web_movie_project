package ra.webmovieapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.webmovieapp.model.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.roleName = 'ROLE_USER'")
    Page<User> findAllUser(Pageable pageable);

    Page<User> findAllByFullNameOrUsernameContainingIgnoreCase(String fullName, String username, Pageable pageable);

    boolean existsByUsername(String username);

    Optional<User> getUserByUsername(String username);
}
