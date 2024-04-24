package ra.webmovieapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.request.MovieRequest;
import ra.webmovieapp.model.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    Page<Movie> getAllMovie(Pageable pageable);

    Optional<Movie> getMovieById(Long movieId);

    Movie save(MovieRequest movieRequest);

    void saveMovie(MovieRequest movieRequest);

    Movie updateMovie(Long movieId, Movie movieReq) throws CustomException;

    void softDeteleByMovieId(Long movieId) throws CustomException;

    void hardDeleteByMovieId(Long movieId) throws CustomException;

    List<Movie> getMovieOnInactive();
    //Tìm kiếm Movie theo Genre and Keyword
    Page<Movie> searchMovieByGenreAndKeyword(Long genreId ,String keyword, Pageable pageable);
}

