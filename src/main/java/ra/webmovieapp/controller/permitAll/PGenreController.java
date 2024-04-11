package ra.webmovieapp.controller.permitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.wrapper.ResponseWrapper;
import ra.webmovieapp.model.entity.Genre;
import ra.webmovieapp.model.enums.EHttpStatus;
import ra.webmovieapp.service.GenreService;

import java.util.List;

@RestController
@RequestMapping("/v1/permit/genre")
@CrossOrigin("*")
public class PGenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<?> getGenreOnActive() throws CustomException{
        List<Genre> genres = genreService.getGenresOnActive();
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        genres
                ), HttpStatus.OK
        );
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchGenre(@RequestParam(name = "name") String nameGenre) throws CustomException{
        List<Genre> genres = genreService.getByNameOrDes(nameGenre, nameGenre);
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        genres
                ), HttpStatus.OK
        );
    }
}
