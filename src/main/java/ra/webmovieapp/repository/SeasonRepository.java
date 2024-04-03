package ra.webmovieapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.webmovieapp.model.entity.Season;
@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    Page<Season> findAll(Pageable pageable);
}
