package ra.webmovieapp.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.request.DayNameRequest;
import ra.webmovieapp.model.dto.request.SeasonRequest;
import ra.webmovieapp.model.entity.Day;
import ra.webmovieapp.model.entity.Movie;
import ra.webmovieapp.model.entity.Season;
import ra.webmovieapp.model.enums.EDayName;
import ra.webmovieapp.model.enums.EMovieStatus;
import ra.webmovieapp.model.enums.EMovieType;
import ra.webmovieapp.repository.DayRepository;
import ra.webmovieapp.repository.GenreRepository;
import ra.webmovieapp.repository.MovieRepository;
import ra.webmovieapp.repository.SeasonRepository;
import ra.webmovieapp.service.SeasonService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeasonServiceImpl implements SeasonService {
    @Autowired
    private SeasonRepository seasonRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private DayRepository dayRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Page<Season> searchSeasonByGenreAndKeyword(String genre, String keyword, Pageable pageable) {
        return seasonRepository.findSeasonsByGenreAndKeyword(genre, keyword, pageable);
    }

    @Override
    public Page<Season> getAllSeason(Pageable pageable) {
        return seasonRepository.findAll(pageable);
    }

    @Override
    public Optional<Season> getSeasonById(Long seasonId) {
        return seasonRepository.findById(seasonId);
    }

    @Override
    public Season add(SeasonRequest seasonRequest) throws CustomException {
        Optional<Movie> movie = movieRepository.findById(seasonRequest.getMovieId());
        if (movie.isEmpty()) throw new CustomException("movie không tồn tại!!");

        Set<Day> days = seasonRequest.getDays()
                .stream()
                .map(day -> dayRepository.findByDayName(EDayName.valueOf(day)).get())
                .collect(Collectors.toSet());

        Season season = Season.builder()
                .nickName(seasonRequest.getNickName())
                .seasonName(seasonRequest.getSeasonName())
                .description(seasonRequest.getDescription())
                .avatar(seasonRequest.getAvatar())
                .status(seasonRequest.getStatus())
                .seasonType(EMovieType.valueOf(seasonRequest.getSeasonType()))
                .seasonStatus(EMovieStatus.valueOf(seasonRequest.getSeasonStatus()))
                .release_date(seasonRequest.getRelease_date())
                .movie(movie.get())
                .days(days)
                .build();
        return seasonRepository.save(season);
    }

    @Override
    public Season updateSeason(Long seasonId, SeasonRequest seasonRequest) throws CustomException {
        Optional<Season> updateSeason = getSeasonById(seasonId);
        if (updateSeason.isEmpty()) throw new CustomException("Phần phim không tồn tại nhaaa!!");

        Season season = updateSeason.get();
        if (seasonRequest.getNickName() != null) season.setNickName(seasonRequest.getNickName());
        if (seasonRequest.getSeasonName() != null) season.setSeasonName(seasonRequest.getSeasonName());
        if (seasonRequest.getDescription() != null) season.setDescription(seasonRequest.getDescription());
        if (seasonRequest.getAvatar() != null) season.setAvatar(seasonRequest.getAvatar());
        if (seasonRequest.getStatus() != null) season.setStatus(seasonRequest.getStatus());
        if (seasonRequest.getSeasonType() != null)
            season.setSeasonType(EMovieType.valueOf(seasonRequest.getSeasonType()));
        if (seasonRequest.getSeasonStatus() != null)
            season.setSeasonStatus(EMovieStatus.valueOf(seasonRequest.getSeasonStatus()));
        if (seasonRequest.getRelease_date() != null) season.setRelease_date(seasonRequest.getRelease_date());
        if (seasonRequest.getMovieId() != null) {
            Optional<Movie> movie = movieRepository.findById(seasonRequest.getMovieId());
            if (movie.isEmpty ()) throw new CustomException("movie không tồn tại!!");
            season.setMovie(movie.get());
        }
        ;
        if (seasonRequest.getDays() != null) {
            Set<Day> days = seasonRequest.getDays()
                    .stream()
                    .map(day -> dayRepository.findByDayName(EDayName.valueOf(day)).get())
                    .collect(Collectors.toSet());
            season.setDays(days);
        }
        ;
        return seasonRepository.save(season);
    }

    @Override
    public Season changeStatus(Long seasonId) throws CustomException {
        Season season = seasonRepository.findById ( seasonId ).orElse ( null );
        if (season == null) throw new CustomException("Không tìm thấy season");
        season.setStatus ( !season.getStatus () );
        return seasonRepository.save ( season );
    }

    @Override
    public Season addDayToSeason(Long seasonId, DayNameRequest dayName) throws CustomException {
        Season season = seasonRepository.findById ( seasonId ).orElse ( null );
        if (season == null) throw new CustomException("Không tìm thấy season");
        Day day = dayRepository.findByDayName(EDayName.valueOf(dayName.getDayName())).orElse(null);
        if (day == null) throw new CustomException("Không tìm thấy day");
        Set<Day> newDaySet = season.getDays();
        newDaySet.add(day);
        season.setDays(newDaySet);
        return seasonRepository.save(season);
    }

    @Override
    public Season deleteDayToSeason(Long seasonId, Long dayId) throws CustomException {
        Season season = seasonRepository.findById ( seasonId ).orElse ( null );
        if (season == null) throw new CustomException("Không tìm thấy season");
        Day day = dayRepository.findById(dayId).orElse(null);
        if (day == null) throw new CustomException("Không tìm thấy day");
        Set<Day> newDaySet = season.getDays();
        newDaySet.remove(day);
        season.setDays(newDaySet);
        return seasonRepository.save(season);
    }

    @Override
    public Page<Season> getAllByStatus(EMovieStatus movieStatus, Pageable pageable) {
        return seasonRepository.findAllBySeasonStatus(movieStatus, pageable);
    }

    @Override
    public Page<Season> searchByNameOrNickName(String keyWord, Pageable pageable) {
        return seasonRepository.searchBySeasonNameOrNickNameContainingIgnoreCase(keyWord, keyWord, pageable);
    }

    @Override
    public Page<Season> getAllByGenreId(Long genreId, Pageable pageable) throws CustomException {
        if (!genreRepository.existsById(genreId)) throw new CustomException("Không tồn tại thể loại này!!");
        return seasonRepository.findAllByGenreId(genreId, pageable);
    }

    @Override
    public Page<Season> getAllByMovieType(EMovieType movieType, Pageable pageable) throws CustomException {
        return seasonRepository.findAllBySeasonType(movieType, pageable);
    }

    @Override
    public Page<Season> getAllByDay(Long dayId, Pageable pageable) throws CustomException {
        if (!dayRepository.existsById(dayId)) throw new CustomException("không tồn tại ngày này");
        return seasonRepository.findAllByDayId(dayId, pageable);
    }

    @Override
    public Season getById(Long seasonId) {
        return seasonRepository.findById(seasonId).orElse(null);
    }

    @Override
    public List<Season> getAllByMovie(Long movieId) throws CustomException {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isEmpty()) throw new CustomException("phim không tồn tại");
        return seasonRepository.findAllByMovie(movie.get());
    }
}
