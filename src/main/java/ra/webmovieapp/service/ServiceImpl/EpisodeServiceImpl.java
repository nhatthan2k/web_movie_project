package ra.webmovieapp.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.request.EpisodeRequest;
import ra.webmovieapp.model.entity.Episode;
import ra.webmovieapp.model.entity.Season;
import ra.webmovieapp.repository.EpisodeRepository;
import ra.webmovieapp.service.EpisodeService;
import ra.webmovieapp.service.SeasonService;

import java.util.List;
import java.util.Optional;

@Service
public class EpisodeServiceImpl implements EpisodeService {
    @Autowired
    private EpisodeRepository episodeRepository;
    @Autowired
    private SeasonService seasonService;

    @Override
    public List<Episode> getAllEpisodeBySeasonId(Long seasonId) {
        return episodeRepository.findAllBySeasonId(seasonId);
    }

    @Override
    public Optional<Episode> getEpisodeById(Long episodeId) {
        return episodeRepository.findById(episodeId);
    }

    @Override
    public Episode add(EpisodeRequest episodeRequest) throws CustomException {
        Optional<Season> season = seasonService.getSeasonById(episodeRequest.getSeasonId());
        if (season.isPresent()) {
            Episode episode = Episode.builder()
                    .numberEpisode(episodeRequest.getNumberEpisode())
                    .source(episodeRequest.getSource())
                    .status(episodeRequest.getStatus())
                    .season(season.get())
                    .build();
            return episodeRepository.save(episode);
        }
        throw new CustomException("Không có phần phim!!");
    }

    @Override
    public Episode updateEpisode(Long episodeId, EpisodeRequest episodeRequest) throws CustomException {
        Optional<Episode> updateEpisode = getEpisodeById(episodeId);
        if (updateEpisode.isEmpty()) throw new CustomException("Tập phim không tồn tại nha!!");
        Episode episode = updateEpisode.get();
        episode.setNumberEpisode(episodeRequest.getNumberEpisode());
        episode.setSource(episodeRequest.getSource());
        return episodeRepository.save(episode);
    }

    @Override
    public Episode changeStatus(Long episodeId) throws CustomException {
        Episode episode = episodeRepository.findById ( episodeId ).orElse ( null );
        if (episode == null) throw new CustomException("Không tìm thấy episode");
        episode.setStatus ( !episode.getStatus () );
        return episodeRepository.save ( episode );
    }
}
