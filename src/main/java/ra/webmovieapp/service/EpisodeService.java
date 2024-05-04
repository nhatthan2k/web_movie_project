package ra.webmovieapp.service;

import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.request.EpisodeRequest;
import ra.webmovieapp.model.entity.Episode;

import java.util.List;
import java.util.Optional;

public interface EpisodeService {
    List<Episode> getAllEpisodeBySeasonId(Long seasonId);

    Optional<Episode> getEpisodeById(Long episodeId);

    Episode add(EpisodeRequest episodeRequest) throws CustomException;

    Episode updateEpisode(Long episodeId, EpisodeRequest episodeRequest) throws CustomException;

    Episode changeStatus(Long episodeId) throws CustomException;
}
