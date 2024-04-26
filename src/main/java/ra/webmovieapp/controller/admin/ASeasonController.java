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
import ra.webmovieapp.model.entity.Movie;
import ra.webmovieapp.model.entity.Season;
import ra.webmovieapp.model.enums.EHttpStatus;
import ra.webmovieapp.service.MovieService;
import ra.webmovieapp.service.SeasonService;

import java.util.Optional;

@RestController
@RequestMapping("/v1/admin/seasons")
@CrossOrigin("*")
public class ASeasonController {
    @Autowired
    private SeasonService seasonService;

    @GetMapping
    public ResponseEntity<?> getAllMoviesToPage(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "nickName", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "order") String order,
            @RequestParam("movie") String movie,
            @RequestParam("search") String keyword
    ) throws CustomException {
        String movieTrim = movie.trim();
        if (movieTrim.isEmpty() || movieTrim.equals("ALL")) {
            movieTrim = null;
        }
        Pageable pageable;
        if (order.equals("asc")) pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        else pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        Page<Season> season = seasonService.searchSeasonByGenreAndKeyword(movieTrim, keyword, pageable);
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        season
                ), HttpStatus.OK
        );
    }

    @GetMapping("/{seasonId}")
    public ResponseEntity<?> getMovieById(@PathVariable("seasonId") String seasonId) throws CustomException {
        try {
            Long id = Long.parseLong(seasonId);
            Optional<Season> season = seasonService.getSeasonById(id);
            if (season.isEmpty()) throw new CustomException("Phần phim không tồn tại!!");
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            season.get()
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }

    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody Season seasonReq) throws CustomException {
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        seasonService.save(seasonReq)
                ),
                HttpStatus.CREATED);
    }

    @PutMapping("/{seasonId}")
    public ResponseEntity<?> updateSeason(
            @PathVariable("seasonId") String updateSeasonId,
            @RequestBody Season season
    ) throws CustomException {
        try {
            Long id = Long.parseLong(updateSeasonId);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            seasonService.updateSeason(id, season)
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }

    @DeleteMapping("/delete/{seasonId}")
    public ResponseEntity<?> hardDeleteMovieById(@PathVariable("seasonId") String deleteSeasonId) throws CustomException {
        try {
            Long id = Long.parseLong(deleteSeasonId);
            seasonService.hardDeleteBySeasonId(id);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            "Delete season successfully"
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }
}
