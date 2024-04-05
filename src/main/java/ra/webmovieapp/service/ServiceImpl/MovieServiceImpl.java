package ra.webmovieapp.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.entity.Movie;
import ra.webmovieapp.repository.MovieRepository;
import ra.webmovieapp.service.MovieService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public Page<Movie> getAllMovie(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Override
    public Optional<Movie> getMovieById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    @Override
    public Movie save(Movie movieReq) {
        Movie movie = Movie.builder()
                .name(movieReq.getName())
                .poster(movieReq.getPoster())
                .description(movieReq.getDescription())
                .build();

        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Long movieId, Movie movieReq) throws CustomException {
        Optional<Movie> updateMovie = getMovieById(movieId);
        if(updateMovie.isEmpty()) throw new CustomException("Phim không tồn tại nhaaa!!");
        Movie movie = updateMovie.get();
        movie.setName(movieReq.getName());
        movie.setPoster(movieReq.getPoster());
        movie.setDescription(movieReq.getDescription());
        return movieRepository.save(movie);
    }

    @Override
    public void softDeteleByMovieId(Long movieId) throws CustomException{
        Optional<Movie> deleteMovie = getMovieById(movieId);
        if(deleteMovie.isEmpty()) throw new CustomException("Phim không tồn tại nhaaa!!");
        Movie movie = deleteMovie.get();
        if(movie.getStatus()){movie.setStatus(false);} throw new CustomException("Phim đã khóa");
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
    public void hardDeleteByMovieId(Long movieId) throws CustomException {
        List<Movie> movieList = getMovieOnInactive();

        for (Movie movie: movieList) {
            if (Objects.equals(movie.getId(), movieId)) {
                movieList.remove(movie);
            } throw new CustomException("Id không nằm trong danh sách được xóa!!");
        }
    }


}
