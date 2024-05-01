package ra.webmovieapp.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.request.GenreId;
import ra.webmovieapp.model.entity.Genre;
import ra.webmovieapp.model.entity.GenreDetail;
import ra.webmovieapp.model.entity.Movie;
import ra.webmovieapp.repository.GenreDetailRepository;
import ra.webmovieapp.repository.GenreRepository;
import ra.webmovieapp.repository.MovieRepository;
import ra.webmovieapp.service.GenreDetailService;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class GenreDetailServiceImpl implements GenreDetailService {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreDetailRepository genreDetailRepository;


    @Override
    public GenreDetail save(Long movieId, Long genreId) throws CustomException {
        Genre genre = genreRepository.findById(genreId).orElse(null);
        if (genre == null) throw new CustomException("Không tìm thấy Genre");

        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) throw new CustomException("Không tìm thấy Movie");

        GenreDetail genreDetail = GenreDetail.builder()
                .genre(genre)
                .movie(movie)
                .build();

        return genreDetailRepository.save(genreDetail);
    }

    @Override
    public void add(Long movieId, Long genreId) throws CustomException {
        Genre genre = genreRepository.findById(genreId).orElse(null);
        if (genre == null) throw new CustomException("Không tìm thấy Genre");
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) throw new CustomException("Không tìm thấy Movie");
        List<GenreDetail> genreDetailList = genreDetailRepository.findByMovie(movie);
        for (GenreDetail genreDetail : genreDetailList) {
            if (!Objects.equals(genreDetail.getGenre().getId(), genreId)) {
                GenreDetail genreDetailNew = GenreDetail.builder()
                        .genre(genre)
                        .movie(movie)
                        .build();
                genreDetailRepository.save(genreDetailNew);
            } else throw new CustomException("Thể loại này đã có rồi!!");
        }

    }

    @Override
    public void delete(Long movieId, Long genreId) throws CustomException {
//        Genre genre = genreRepository.findById( genreId ).orElse(null);
//        if (genre == null) throw new CustomException("Không tìm thấy Genre");
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) throw new CustomException("Không tìm thấy Movie");
        List<GenreDetail> genreDetailList = genreDetailRepository.findByMovie(movie);
        for (GenreDetail genreDetail : genreDetailList) {
            if (Objects.equals(genreDetail.getGenre().getId(), genreId)) {
                genreDetailRepository.delete(genreDetail);
            }
        }
    }
}
