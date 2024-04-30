package ra.webmovieapp.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.mapper.MovieMapper;
import ra.webmovieapp.model.dto.request.MovieRequest;
import ra.webmovieapp.model.dto.response.MovieResponse;
import ra.webmovieapp.model.dto.wrapper.ResponseWrapper;
import ra.webmovieapp.model.entity.Movie;
import ra.webmovieapp.model.enums.EHttpStatus;
import ra.webmovieapp.repository.MovieRepository;
import ra.webmovieapp.service.MovieService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/admin/movies")
@CrossOrigin("*")
public class AMovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieMapper movieMapper;

    @GetMapping
    public ResponseEntity<?> getAllMoviesToPage(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "id", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "order") String order,
            @RequestParam("genre") String genre,
            @RequestParam("search") String keyword
    ) {
        String genreTrim = genre.trim();
        if (genreTrim.isEmpty() || genreTrim.equals("ALL")) {
            genreTrim = null;
        }
        Pageable pageable;
        if (order.equals("asc")) pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        else pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        Page<Movie> movies = movieService.searchMovieByGenreAndKeyword(genreTrim, keyword, pageable);
        Page<MovieResponse> movieResponses = movies.map(movie -> movieMapper.mapMovieTo(movie));
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        movieResponses
                ), HttpStatus.OK
        );
    }

    @GetMapping("/no_page")
    public ResponseEntity<?> getAllNoPage(@RequestParam("search") String search) {
        List<Movie> movies = movieService.getAllMovie(search);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovieById(@PathVariable("movieId") String movieId) throws CustomException {
        try {
            Long id = Long.parseLong(movieId);
            Optional<Movie> movie = movieService.getMovieById(id);
            if (movie.isEmpty()) throw new CustomException("Phim không tồn tại!!");
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            movie.get()
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }

    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody MovieRequest movieReq) throws CustomException {
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        movieService.save(movieReq)
                ),
                HttpStatus.CREATED);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<?> updateMovie(
            @PathVariable("movieId") String updateMovieId,
            @RequestBody MovieRequest movieRequest
    ) throws CustomException {
        try {
            Long id = Long.parseLong(updateMovieId);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            movieService.updateMovie(id, movieRequest)
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }


    @PutMapping("/{movieId}/status")
    public ResponseEntity<?> switchMovieStatus(@PathVariable("movieId") String movieId) throws CustomException {
        try {
            Long id = Long.parseLong(movieId);
            Movie movie = movieService.changeMovieStatus(id);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            movie
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nha");
        }
    }


    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> softDeleteMovieById(@PathVariable("movieId") String deleteMovieId) throws CustomException {
        try {
            Long id = Long.parseLong(deleteMovieId);
            movieService.softDeteleByMovieId(id);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            "Delete successfully"
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }

    @GetMapping("/Inactive")
    public ResponseEntity<?> getMovieOnInactive() throws CustomException {
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        movieService.getMovieOnInactive()
                ), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<?> hardDeleteMovieById(@PathVariable("movieId") String deleteMovieId) throws CustomException {
        try {
            Long id = Long.parseLong(deleteMovieId);
            movieService.hardDeleteByMovieId(id);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            "Delete successfully"
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }
}
