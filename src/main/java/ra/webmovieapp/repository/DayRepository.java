package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.webmovieapp.model.entity.Day;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {
}
