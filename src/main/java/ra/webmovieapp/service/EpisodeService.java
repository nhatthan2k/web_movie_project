package ra.webmovieapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.entity.Episode;

import java.util.Optional;

public interface EpisodeService {
    Page<Episode> getAllEpisodeBySeasonId(Pageable pageable, Long seasonId);
    Optional<Episode> getEpisodeById(Long episodeId);
    Episode save(Episode episodeReq);
    Episode updateEpisode(Long episodeId, Episode episodeReq) throws CustomException;
    void hardDeleteByEpisodeId(Long episodeId) throws CustomException;
}
