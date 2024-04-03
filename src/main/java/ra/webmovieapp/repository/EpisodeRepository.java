package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.webmovieapp.model.entity.Episode;
@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}
