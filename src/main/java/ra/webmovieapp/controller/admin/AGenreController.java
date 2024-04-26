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
import ra.webmovieapp.model.dto.wrapper.ResponseWrapper;
import ra.webmovieapp.model.entity.Genre;
import ra.webmovieapp.model.enums.EHttpStatus;
import ra.webmovieapp.service.GenreService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/admin/genres")
@CrossOrigin("*")
public class AGenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<?> getAllGenresToPage(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "id", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "order") String order,
            @RequestParam("search") String search
    ) throws CustomException {
        Pageable pageable;
        if (order.equals("asc")) pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        else pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        Page<Genre> genres = genreService.searchGenreByGenreName(search, pageable);
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        genres
                ), HttpStatus.OK
        );
    }

    @GetMapping("/no-page")
    public ResponseEntity<?> getAllGenreNoPage(@RequestParam("search") String search) {
        List<Genre> genres = genreService.getAllGenres(search);
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        genres
                ), HttpStatus.OK
        );
    }

    @GetMapping("/{genreId}")
    public ResponseEntity<?> getGenreById(@PathVariable("genreId") String genreId) throws CustomException {
        try {
            Long id = Long.parseLong(genreId);
            Optional<Genre> genre = genreService.getGenreById(id);
            if (genre.isEmpty()) throw new CustomException("Thể loại không tồn tại!!");
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            genre.get()
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }

    @PostMapping
    public ResponseEntity<?> createGenre(@RequestBody Genre genreReq) throws CustomException {
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        genreService.save(genreReq)
                ),
                HttpStatus.CREATED);
    }

    @PutMapping("/{genreId}")
    public ResponseEntity<?> updateGenre(
            @PathVariable("genreId") String updateGenreId,
            @RequestBody Genre genreReq
    ) throws CustomException {
        try {
            Long id = Long.parseLong(updateGenreId);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            genreService.updateGenre(id, genreReq)
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }

    @PutMapping("/{genreId}/status")
    public ResponseEntity<?> softDeleteGenreById(@PathVariable("genreId") String deleteGenreId) throws CustomException {
        try {
            Long id = Long.parseLong(deleteGenreId);
            Genre updateGenre = genreService.changeStatusByGenreId(id);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            updateGenre
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }

    @DeleteMapping("/delete/{genreId}")
    public ResponseEntity<?> hardDeleteMovieById(@PathVariable("genreId") String deleteGenreId) throws CustomException {
        try {
            Long id = Long.parseLong(deleteGenreId);
            genreService.DeleteByGenreId(id);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            "Delete class successfully"
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }
}
