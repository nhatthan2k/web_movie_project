package ra.webmovieapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.webmovieapp.model.entity.Genre;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Page<Genre> findAll(Pageable pageable);

    Page<Genre> findAllByGenreNameContainingIgnoreCase(String genreName, Pageable pageable);

    List<Genre> findAllByGenreNameContainingIgnoreCase(String genreName);

    List<Genre> findByStatus(Boolean status);

    @Query("select g from Genre g where g.status = true and g.genreName LIKE %?1%")
    List<Genre> findGenreOnActivebyNameAndDes(String name, String description);
}