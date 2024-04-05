package ra.webmovieapp.service.ServiceImpl;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.entity.Genre;
import ra.webmovieapp.repository.GenreRepository;
import ra.webmovieapp.service.GenreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreRepository genreRepository;
    @Override
    public Page<Genre> getAllGenres(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }

    @Override
    public Optional<Genre> getGenreById(Long genreId) {
        return genreRepository.findById(genreId);
    }

    @Override
    public Genre save(Genre genreReq) {
        Genre genre = Genre.builder()
                .genreName(genreReq.getGenreName())
                .description(genreReq.getDescription())
                .status(genreReq.getStatus())
                .build();
        return genreRepository.save(genre) ;
    }

    @Override
    public Genre updateGenre(Long genreId, Genre genreReq) throws CustomException {
        Optional<Genre> updateGenre = getGenreById(genreId);
        if (updateGenre.isEmpty()) throw new CustomException("Thể loại không tồn tại nha!!");
        Genre genre = updateGenre.get();
        genre.setGenreName(genreReq.getGenreName());
        genre.setDescription(genreReq.getDescription());
        genre.setStatus(genreReq.getStatus());
        return genreRepository.save(genre);
    }

    @Override
    public void softDeteleByGenreId(Long genreId) throws CustomException {
        Optional<Genre> deleteGenre = getGenreById(genreId);
        if(deleteGenre.isEmpty()) throw new CustomException("Thể loại không tồn tại nhaaa!!");
        Genre genre = deleteGenre.get();
        if(genre.getStatus()){genre.setStatus(false);} throw new CustomException("Thể loại đã khóa");
    }

    @Override
    public void hardDeleteByGenreId(Long genreId) throws CustomException {
        if (!genreRepository.existsById(genreId)) throw new CustomException("Không thấy ID nhaaa!!");
        genreRepository.deleteById(genreId);
    }

    @Override
    public List<Genre> getGenresOnActive() {
        return genreRepository.findByStatus(true);
    }

    @Override
    public List<Genre> getByNameOrDes(String name, String description) {
        return genreRepository.findGenreOnActivebyNameAndDes(name, description);
    }
}
