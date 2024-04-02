package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.webmovieapp.model.entity.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
