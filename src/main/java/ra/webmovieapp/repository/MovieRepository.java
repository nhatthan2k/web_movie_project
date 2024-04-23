package ra.webmovieapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.webmovieapp.model.entity.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findAll(Pageable pageable);

    List<Movie> findAllByStatus(Boolean status);

    // Tìm kiếm Movie theo Genre and Keyword
    @Query("select m from GenreDetail gd join Movie m on m.id = gd.movie.id where (:genreId is null OR gd.genre.id = :genreId) AND (:keyword is null or m.movieName like concat('%',:keyword,'%') ESCAPE '!')")
    Page<Movie> findMoviesByGenreAndKeyword(Long genreId, String keyword, Pageable pageable);
}
