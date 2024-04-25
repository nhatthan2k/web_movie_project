package ra.webmovieapp.model.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.webmovieapp.model.dto.response.MovieResponse;
import ra.webmovieapp.model.entity.Genre;
import ra.webmovieapp.model.entity.GenreDetail;
import ra.webmovieapp.model.entity.Movie;
import ra.webmovieapp.repository.GenreDetailRepository;
import ra.webmovieapp.service.MovieService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {
    @Autowired
    private GenreDetailRepository genreDetailRepository;
    public MovieResponse mapMovieTo(Movie movie) {
        List<GenreDetail> genreDetails = genreDetailRepository.findByMovie(movie);
        List<Genre> genres = mapGenreDetailToGenre(genreDetails);
        return MovieResponse.builder()
                .movie(movie)
                .Genres(genres)
                .build();
    }

    public List<Genre> mapGenreDetailToGenre(List<GenreDetail> genreDetails) {
        return genreDetails.stream().map(GenreDetail::getGenre).collect(Collectors.toList());
    }
}
