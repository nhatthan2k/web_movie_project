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
import ra.webmovieapp.model.dto.request.DayNameRequest;
import ra.webmovieapp.model.dto.request.SeasonRequest;
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
            @RequestParam(defaultValue = "id", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "order") String order,
            @RequestParam("movie") String movie,
            @RequestParam("search") String keyword
    ) {
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
    public ResponseEntity<?> createMovie(@RequestBody SeasonRequest seasonRequest) throws CustomException {
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        seasonService.add(seasonRequest)
                ),
                HttpStatus.CREATED);
    }

    @PutMapping("/{seasonId}")
    public ResponseEntity<?> updateSeason(
            @PathVariable("seasonId") String updateSeasonId,
            @RequestBody SeasonRequest seasonRequest
    ) throws CustomException {
        try {
            Long id = Long.parseLong(updateSeasonId);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            seasonService.updateSeason(id, seasonRequest)
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }

    @PutMapping("/{seasonId}/status")
    public ResponseEntity<?> hardDeleteMovieById(@PathVariable("seasonId") String SeasonId) throws CustomException {
        try {
            Long id = Long.parseLong(SeasonId);
            Season season = seasonService.changeStatus(id);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            season
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }

    //  add day to movie
    @PostMapping("/{seasonId}/day")
    public ResponseEntity<?> addDayToSeason(
            @PathVariable("seasonId") String seasonId,
            @RequestBody DayNameRequest dayName) throws CustomException {
        try {
            Long id = Long.parseLong(seasonId);
            Season season = seasonService.addDayToSeason(id, dayName);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            season
                    ), HttpStatus.CREATED);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }

    @DeleteMapping("/{seasonId}/day/{dayId}")
    public ResponseEntity<?> deleteDayToSeason(
            @PathVariable("seasonId") String seasonId,
            @PathVariable("dayId") String dayId) throws CustomException {
        try {
            Long deleteSeasonId = Long.parseLong(seasonId);
            Long deleteDayId = Long.parseLong(dayId);
            Season season = seasonService.deleteDayToSeason(deleteSeasonId, deleteDayId);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            season
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }
}
