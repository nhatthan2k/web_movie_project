package ra.webmovieapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.entity.Season;

import java.util.Optional;

public interface SeasonService {
    Page<Season> getAllSeason(Pageable pageable);

    Optional<Season> getSeasonById(Long seasonId);

    Season save(Season seasonRep);

    Season updateSeason(Long seasonId, Season seasonReq) throws CustomException;

    void hardDeleteBySeasonId(Long seasonId) throws CustomException;
}
