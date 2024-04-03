package ra.webmovieapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.entity.Genre;

import java.util.Optional;

public interface GenreService {
    Page<Genre> getAllGenres(Pageable pageable);

    Optional<Genre> getGenreById(Long genreId);

    Genre save(Genre genreReq);

    Genre updateGenre(Long genreId, Genre genreReq) throws CustomException;

    void softDeteleByGenreId(Long genreId) throws CustomException;

    void hardDeleteByGenreId(Long genreId) throws CustomException;

}
