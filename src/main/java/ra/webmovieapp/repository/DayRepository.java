package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.webmovieapp.model.entity.Day;

public interface DayRepository extends JpaRepository<Day, Long> {
}
