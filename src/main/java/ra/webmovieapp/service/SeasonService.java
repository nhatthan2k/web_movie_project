package ra.webmovieapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.request.DayNameRequest;
import ra.webmovieapp.model.dto.request.SeasonRequest;
import ra.webmovieapp.model.entity.Movie;
import ra.webmovieapp.model.entity.Season;
import ra.webmovieapp.model.enums.EMovieStatus;
import ra.webmovieapp.model.enums.EMovieType;

import java.util.List;
import java.util.Optional;

public interface SeasonService {
    Page<Season> searchSeasonByGenreAndKeyword(String genre, String keyword, Pageable pageable);

    Page<Season> getAllSeason(Pageable pageable);

    Optional<Season> getSeasonById(Long seasonId);

    Season add(SeasonRequest seasonRequest) throws CustomException;

    Season updateSeason(Long seasonId, SeasonRequest seasonRequest) throws CustomException;

    Season changeStatus(Long seasonId) throws CustomException;

    Season addDayToSeason(Long seasonId, DayNameRequest dayName) throws CustomException;

    Season deleteDayToSeason(Long seasonId, Long dayId) throws CustomException;
    //  PermitAll
    Page<Season> getAllByStatus(EMovieStatus movieStatus, Pageable pageable);

    Page<Season> searchByNameOrNickName(String keyWord, Pageable pageable);

    Page<Season> getAllByGenreId(Long genreId, Pageable pageable) throws CustomException;

    Page<Season> getAllByMovieType(EMovieType movieType, Pageable pageable) throws CustomException;

    Page<Season> getAllByDay(Long dayId, Pageable pageable) throws CustomException;

    Season getById(Long seasonId);

    List<Season> getAllByMovie(Long movieId) throws CustomException;
}
