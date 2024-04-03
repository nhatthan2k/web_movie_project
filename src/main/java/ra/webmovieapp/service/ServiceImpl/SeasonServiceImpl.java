package ra.webmovieapp.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.entity.Movie;
import ra.webmovieapp.model.entity.Season;
import ra.webmovieapp.repository.SeasonRepository;
import ra.webmovieapp.service.SeasonService;

import java.util.Optional;

@Service
public class SeasonServiceImpl implements SeasonService {
    @Autowired
    private SeasonRepository seasonRepository;
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
                .name(seasonRep.getName())
                .description(seasonRep.getDescription())
                .avatar(seasonRep.getAvatar())
                .movieType(seasonRep.getMovieType())
                .release_date(seasonRep.getRelease_date())
                .build();
        return seasonRepository.save(season) ;
    }

    @Override
    public Season updateSeason(Long seasonId, Season seasonReq) throws CustomException {
        Optional<Season> updateSeason = getSeasonById(seasonId);
        if(updateSeason.isEmpty()) throw new CustomException("Phần phim không tồn tại nhaaa!!");
        Season season = updateSeason.get();
        season.setNickName(seasonReq.getNickName());
        season.setName(seasonReq.getName());
        season.setDescription(seasonReq.getDescription());
        season.setAvatar(seasonReq.getAvatar());
        season.setMovieType(seasonReq.getMovieType());
        season.setRelease_date(seasonReq.getRelease_date());
        return seasonRepository.save(season);
    }

    @Override
    public void hardDeleteBySeasonId(Long seasonId) throws CustomException {
        if (!seasonRepository.existsById(seasonId)) throw new CustomException("Không thấy ID nhaaa!!");
        seasonRepository.deleteById(seasonId);
    }
}
