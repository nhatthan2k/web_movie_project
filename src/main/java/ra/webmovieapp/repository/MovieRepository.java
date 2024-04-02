package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.webmovieapp.model.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
