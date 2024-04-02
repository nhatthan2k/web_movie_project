package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.webmovieapp.model.entity.Season;

public interface SeasonRepository extends JpaRepository<Season, Long> {
}
