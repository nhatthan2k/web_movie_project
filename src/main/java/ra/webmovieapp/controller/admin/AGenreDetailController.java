package ra.webmovieapp.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.wrapper.ResponseWrapper;
import ra.webmovieapp.model.entity.GenreDetail;
import ra.webmovieapp.model.entity.Season;
import ra.webmovieapp.model.enums.EHttpStatus;
import ra.webmovieapp.service.GenreDetailService;

import java.util.Optional;

@RestController
@RequestMapping("/v1/admin")
public class AGenreDetailController {

    @Autowired
    private GenreDetailService genreDetailService;

    @PostMapping("/movie/{movieId}/genre/{genreId}")
    public ResponseEntity<?> addGenreDetail(@PathVariable("movieId") String movie,
                                            @PathVariable("genreId") String genre) throws CustomException, NumberFormatException {
        try {
            Long moiveId = Long.parseLong(movie);
            Long genreId = Long.parseLong(genre);
            genreDetailService.add(moiveId, genreId);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            "Add successfully"
                    ), HttpStatus.OK);

        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }

    }

    @DeleteMapping("/movie/{movieId}/genre/{genreId}")
    public ResponseEntity<?> deleteGenreDetail(@PathVariable("movieId") String movie,
                                            @PathVariable("genreId") String genre) throws CustomException, NumberFormatException {
        try {
            Long moiveId = Long.parseLong(movie);
            Long genreId = Long.parseLong(genre);
            genreDetailService.delete(moiveId, genreId);
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
