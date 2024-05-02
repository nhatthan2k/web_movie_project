package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.webmovieapp.model.entity.Day;
import ra.webmovieapp.model.enums.EDayName;

import java.util.Optional;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {
    Optional<Day> findByDayName(EDayName dayName);
}
