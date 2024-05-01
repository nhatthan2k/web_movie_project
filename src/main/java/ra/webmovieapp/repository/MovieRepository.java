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
    List<Movie> findAllByMovieNameContainingIgnoreCase(String movieName);

    List<Movie> findAllByStatus(Boolean status);

    Page<Movie> findAll(Pageable pageable);

    // Tìm kiếm Movie theo Genre and Keyword
    @Query("select m from Movie m join GenreDetail gd on m.id = gd.movie.id where (:genre is null OR gd.genre.genreName = :genre) AND (:keyword is null or m.movieName like concat('%',:keyword,'%') ESCAPE '!')")
    Page<Movie> findMoviesByGenreAndKeyword(String genre, String keyword, Pageable pageable);
}
