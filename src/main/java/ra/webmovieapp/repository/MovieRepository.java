package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.webmovieapp.model.entity.Movie;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
