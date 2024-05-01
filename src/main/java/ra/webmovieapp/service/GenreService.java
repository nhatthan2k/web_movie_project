package ra.webmovieapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<Genre> getAllGenres(String keyword);

    Page<Genre> searchGenreByGenreName(String keyword, Pageable pageable);

    Optional<Genre> getGenreById(Long genreId);

    Genre save(Genre genreReq);

    Genre updateGenre(Long genreId, Genre genreReq) throws CustomException;

    Genre changeStatusByGenreId(Long genreId) throws CustomException;

    void DeleteByGenreId(Long genreId) throws CustomException;

    List<Genre> getGenresOnActive();

    List<Genre> getByNameOrDes(String name, String description);
}
