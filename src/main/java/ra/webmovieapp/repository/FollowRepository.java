package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.webmovieapp.model.entity.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
}
