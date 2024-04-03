package ra.webmovieapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.webmovieapp.model.entity.Episode;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    @Query("select e from Episode e WHERE e.season.id = :seasonId")
    Page<Episode> findAllBySeasonId(Pageable pageable, Long seasonId);
}
