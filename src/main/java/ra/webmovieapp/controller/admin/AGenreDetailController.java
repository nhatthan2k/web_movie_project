package ra.webmovieapp.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.request.GenreId;
import ra.webmovieapp.model.dto.wrapper.ResponseWrapper;
import ra.webmovieapp.model.enums.EHttpStatus;
import ra.webmovieapp.service.GenreDetailService;


@RestController
@RequestMapping("/v1/admin/movie")
@CrossOrigin("*")
public class AGenreDetailController {

    @Autowired
    private GenreDetailService genreDetailService;

    @PostMapping("/{movieId}/genre")
    public ResponseEntity<?> addGenreDetail(@PathVariable("movieId") String movie,
                                            @RequestBody GenreId genreId) throws CustomException, NumberFormatException {
        try {
            Long movieId = Long.parseLong(movie);
            genreDetailService.add(movieId, genreId.getId());
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            "Add successfully"
                    ), HttpStatus.CREATED);

        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }

    @DeleteMapping("/{movieId}/genre/{genreId}")
    public ResponseEntity<?> deleteGenreDetail(@PathVariable("movieId") String movie,
                                               @PathVariable("genreId") String genre) throws CustomException, NumberFormatException {
        try {
            Long movieId = Long.parseLong(movie);
            Long genreId = Long.parseLong(genre);
            genreDetailService.delete(movieId, genreId);
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
