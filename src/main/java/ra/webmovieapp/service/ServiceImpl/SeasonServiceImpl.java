package ra.webmovieapp.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.entity.Movie;
import ra.webmovieapp.model.entity.Season;
import ra.webmovieapp.model.enums.EMovieStatus;
import ra.webmovieapp.model.enums.EMovieType;
import ra.webmovieapp.repository.DayRepository;
import ra.webmovieapp.repository.GenreRepository;
import ra.webmovieapp.repository.MovieRepository;
import ra.webmovieapp.repository.SeasonRepository;
import ra.webmovieapp.service.SeasonService;

import java.util.List;
import java.util.Optional;

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
    public Season save(Season seasonRep) {
        Season season = Season.builder()
                .nickName(seasonRep.getNickName())
                .seasonName(seasonRep.getSeasonName())
                .description(seasonRep.getDescription())
                .avatar(seasonRep.getAvatar())
                .status(true)
                .seasonType(seasonRep.getSeasonType())
                .seasonStatus(seasonRep.getSeasonStatus())
                .release_date(seasonRep.getRelease_date())
                .build();
        return seasonRepository.save(season);
    }

    @Override
    public Season updateSeason(Long seasonId, Season seasonReq) throws CustomException {
        Optional<Season> updateSeason = getSeasonById(seasonId);
        if (updateSeason.isEmpty()) throw new CustomException("Phần phim không tồn tại nhaaa!!");
        Season season = updateSeason.get();
        season.setNickName(seasonReq.getNickName());
        season.setSeasonName(seasonReq.getSeasonName());
        season.setDescription(seasonReq.getDescription());
        season.setAvatar(seasonReq.getAvatar());
        season.setSeasonType(seasonReq.getSeasonType());
        season.setRelease_date(seasonReq.getRelease_date());
        return seasonRepository.save(season);
    }

    @Override
    public void hardDeleteBySeasonId(Long seasonId) throws CustomException {
        if (!seasonRepository.existsById(seasonId)) throw new CustomException("Không thấy ID nhaaa!!");
        seasonRepository.deleteById(seasonId);
    }

    @Override
    public Page<Season> getAllByStatus(EMovieStatus movieStatus, Pageable pageable) {
        return seasonRepository.findAllByMovieStatus(movieStatus, pageable);
    }

    @Override
    public Page<Season> searchByNameOrNickName(String keyWord, Pageable pageable) {
        return seasonRepository.searchBySeasonNameOrNickNameContainingIgnoreCase(keyWord, keyWord, pageable);
    }

    @Override
    public Page<Season> getAllByGenreId(Long genreId, Pageable pageable) throws CustomException {
        if (!genreRepository.existsById(genreId)) throw new CustomException("Không tồn tại thể loại này!!");
        return seasonRepository.findAllByGenreId(genreId,pageable);
    }

    @Override
    public Page<Season> getAllByMovieType(EMovieType movieType, Pageable pageable) throws CustomException {
        return seasonRepository.findAllByMovieType(movieType, pageable);
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
