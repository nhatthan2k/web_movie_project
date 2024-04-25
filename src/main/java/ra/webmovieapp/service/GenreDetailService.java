package ra.webmovieapp.service;

import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.entity.GenreDetail;

public interface GenreDetailService {
    GenreDetail save(Long movieId, Long genreId) throws CustomException;
}
