package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.webmovieapp.model.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
