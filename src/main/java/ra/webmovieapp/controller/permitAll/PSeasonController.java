package ra.webmovieapp.controller.permitAll;

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
import ra.webmovieapp.model.entity.Season;
import ra.webmovieapp.model.enums.EHttpStatus;
import ra.webmovieapp.model.enums.EMovieStatus;
import ra.webmovieapp.model.enums.EMovieType;
import ra.webmovieapp.service.MovieService;
import ra.webmovieapp.service.SeasonService;

import java.util.List;

@RestController
@RequestMapping("/v1/permit/seasons")
@CrossOrigin("*")
public class PSeasonController {
    @Autowired
    private SeasonService seasonService;

    @GetMapping("")
    public ResponseEntity<?> getAllSeasonShowing(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "nickName", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "order") String order
    ) throws CustomException {
        Pageable pageable;
        if (order.equals("asc")) pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        else pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        Page<Season> seasons = seasonService.getAllByStatus(EMovieStatus.SHOWING, pageable);
        if (seasons.getContent().isEmpty()) throw new CustomException("Season rỗng nhaaa");
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        seasons.getContent()
                ), HttpStatus.OK
        );
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchSeasonByName(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "nickName", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "order") String order,
            @RequestParam("name") String keyWord
    ) throws CustomException {
        Pageable pageable;
        if (order.equals("asc")) pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        else pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        Page<Season> seasons = seasonService.searchByNameOrNickName(keyWord, pageable);
        if (seasons.getContent().isEmpty()) throw new CustomException("Season rỗng nhaaa");
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        seasons.getContent()
                ), HttpStatus.OK
        );
    }

    @GetMapping("/genre/{genreId}")
    public ResponseEntity<?> getAllSeasonByGenreId(
            @PathVariable("genreId") String genreId,
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "nickName", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "order") String order
    ) throws CustomException {
        Pageable pageable;
        if (order.equals("asc")) pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        else pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        try {
            Long id = Long.parseLong(genreId);
            Page<Season> seasons = seasonService.getAllByGenreId(id, pageable);
            if (seasons.getContent().isEmpty()) throw new CustomException("Season rỗng nhaaa");
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            seasons.getContent()
                    ), HttpStatus.OK
            );
        } catch (NumberFormatException e) {
            throw new CustomException("đinh dạng sai rồi nha!!");
        }
    }

    @GetMapping("status/{status}")
    public ResponseEntity<?> getAllByStatus(
            @PathVariable("status") EMovieStatus status,
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "nickName", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "order") String order
    ) throws CustomException {
        Pageable pageable;
        if (order.equals("asc")) pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        else pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        Page<Season> seasons = seasonService.getAllByStatus(status, pageable);
        if (seasons.getContent().isEmpty()) throw new CustomException("Season rỗng nhaaa");
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        seasons.getContent()
                ), HttpStatus.OK
        );
    }

    @GetMapping("type/{type}")
    public ResponseEntity<?> getAllByType(
            @PathVariable("type") EMovieType type,
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "nickName", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "order") String order
    ) throws CustomException {
        Pageable pageable;
        if (order.equals("asc")) pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        else pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        Page<Season> seasons = seasonService.getAllByMovieType(type, pageable);
        if (seasons.getContent().isEmpty()) throw new CustomException("Season rỗng nhaaa");
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        seasons.getContent()
                ), HttpStatus.OK
        );
    }

    @GetMapping("day/{dayId}")
    public ResponseEntity<?> getAllByDay(
            @PathVariable("dayId") String dayId,
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "nickName", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "order") String order
    ) throws CustomException {
        Pageable pageable;
        if (order.equals("asc")) pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        else pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        try {
            Long id = Long.parseLong(dayId);
            Page<Season> seasons = seasonService.getAllByDay(id, pageable);
            if (seasons.getContent().isEmpty()) throw new CustomException("Season rỗng nhaaa");
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            seasons.getContent()
                    ), HttpStatus.OK
            );
        } catch (NumberFormatException e) {
            throw new CustomException("định dạng sai đường dẫn rồi nha");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String seasonId) throws CustomException {
        try {
            Long id = Long.parseLong(seasonId);
            Season season = seasonService.getById(id);
            if (season == null) throw new CustomException("Không tồn tại phần phim này!");
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            season
                    ), HttpStatus.OK
            );
        } catch (NumberFormatException e) {
            throw new CustomException("định dạng sai đường dẫn rồi nha");
        }
    }

    @GetMapping("movie/{movieId}")
    public ResponseEntity<?> getAllByMovie(@PathVariable("movieId") String movieId) throws CustomException {
        try {
            Long id = Long.parseLong(movieId);
            List<Season> seasons = seasonService.getAllByMovie(id);
            if (seasons.isEmpty()) throw new CustomException("Season rỗng nhaaa");
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            seasons
                    ), HttpStatus.OK
            );
        } catch (NumberFormatException e) {
            throw new CustomException("định dạng sai đường dẫn rồi nha");
        }
    }
}
