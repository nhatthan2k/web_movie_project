package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.webmovieapp.model.entity.Genre;
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
