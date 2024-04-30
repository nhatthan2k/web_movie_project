package ra.webmovieapp.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.request.GenreId;
import ra.webmovieapp.model.dto.request.MovieRequest;
import ra.webmovieapp.model.entity.Movie;
import ra.webmovieapp.repository.MovieRepository;
import ra.webmovieapp.service.GenreDetailService;
import ra.webmovieapp.service.MovieService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreDetailService genreDetailService;

    @Override
    public List<Movie> getAllMovie(String keyWord) {
        if (keyWord.isEmpty()) {
            return movieRepository.findAll();
        }
        return movieRepository.findAllByMovieNameContainingIgnoreCase(keyWord);
    }

    @Override
    public Optional<Movie> getMovieById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    @Override
    public Movie save(MovieRequest movieRequest) throws CustomException {
        Movie movie = Movie.builder()
                .movieName(movieRequest.getMovieName())
                .poster(movieRequest.getPoster())
                .description(movieRequest.getDescription())
                .status(movieRequest.getStatus())
                .build();
        Movie movieNew = movieRepository.save(movie);
        for (GenreId genreId : movieRequest.getGenreId()) {
            genreDetailService.save(movieNew.getId(), genreId.getId());
        }
        return movieNew;
    }

    @Override
    public Movie updateMovie(Long movieId, MovieRequest movieReq) throws CustomException {
        Optional<Movie> updateMovie = getMovieById(movieId);
        if (updateMovie.isEmpty()) throw new CustomException("Phim không tồn tại nhaaa!!");
        Movie movie = updateMovie.get();
        if (movieReq.getMovieName() != null) movie.setMovieName(movieReq.getMovieName());
        if (movieReq.getPoster() != null) movie.setPoster(movieReq.getPoster());
        if (movieReq.getDescription() != null) movie.setDescription(movieReq.getDescription());
        return movieRepository.save(movie);
    }

    @Override
    public Movie changeMovieStatus(Long movieId) throws CustomException {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) throw new CustomException("Không tìm thấy movie");
        movie.setStatus(!movie.getStatus());

        return movieRepository.save(movie);
    }

    @Override
    public void softDeteleByMovieId(Long movieId) throws CustomException {
        Optional<Movie> deleteMovie = getMovieById(movieId);
        if (deleteMovie.isEmpty()) throw new CustomException("Phim không tồn tại nhaaa!!");
        Movie movie = deleteMovie.get();
        if (movie.getStatus()) {
            movie.setStatus(false);
        }
        throw new CustomException("Phim đã khóa");
    }

//    @Override
//    public void hardDeleteByMovieId(Long movieId) throws CustomException {
//        if (!movieRepository.existsById(movieId)) throw new CustomException("Không thấy ID nhaaa!!");
//        movieRepository.deleteById(movieId);
//    }

    @Override
    public List<Movie> getMovieOnInactive() {
        return movieRepository.findAllByStatus(false);
    }

    @Override
    public Page<Movie> searchMovieByGenreAndKeyword(String genre, String keyword, Pageable pageable) {
        if (genre == null && keyword.isEmpty()) {
            return movieRepository.findAll(pageable);
        }
        return movieRepository.findMoviesByGenreAndKeyword(genre, keyword, pageable);
    }

    @Override
    public void hardDeleteByMovieId(Long movieId) throws CustomException {
        List<Movie> movieList = getMovieOnInactive();

        for (Movie movie : movieList) {
            if (Objects.equals(movie.getId(), movieId)) {
                movieList.remove(movie);
            }
            throw new CustomException("Id không nằm trong danh sách được xóa!!");
        }
    }


}
