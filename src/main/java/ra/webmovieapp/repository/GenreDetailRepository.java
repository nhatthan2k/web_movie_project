package ra.webmovieapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ra.webmovieapp.model.entity.GenreDetail;
import ra.webmovieapp.model.entity.GenreDetailId;
import ra.webmovieapp.model.entity.Movie;

public interface GenreDetailRepository extends JpaRepository<GenreDetail, GenreDetailId> {
    Page<Movie> findMovieByGenreId(Long genre_id, Pageable pageable);
}
