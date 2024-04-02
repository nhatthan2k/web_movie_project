package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.webmovieapp.model.entity.Episode;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}
