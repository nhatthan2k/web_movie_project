package ra.webmovieapp.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.entity.Episode;
import ra.webmovieapp.repository.EpisodeRepository;
import ra.webmovieapp.service.EpisodeService;

import java.util.Optional;

@Service
public class EpisodeServiceImpl implements EpisodeService {
    @Autowired
    private EpisodeRepository episodeRepository;

    @Override
    public Page<Episode> getAllEpisodeBySeasonId(Pageable pageable, Long seasonId) {
        return episodeRepository.findAllBySeasonId(pageable, seasonId);
    }

    @Override
    public Optional<Episode> getEpisodeById(Long episodeId) {
        return episodeRepository.findById(episodeId);
    }

    @Override
    public Episode save(Episode episodeReq) {
        Episode episode = Episode.builder()
                .numberEpisode(episodeReq.getNumberEpisode())
                .source(episodeReq.getSource())
                .build();
        return episodeRepository.save(episode);
    }

    @Override
    public Episode updateEpisode(Long episodeId, Episode episodeReq) throws CustomException {
        Optional<Episode> updateEpisode = getEpisodeById(episodeId);
        if (updateEpisode.isEmpty()) throw new CustomException("Tập phim không tồn tại nha!!");
        Episode episode = updateEpisode.get();
        episode.setNumberEpisode(episodeReq.getNumberEpisode());
        episode.setSource(episodeReq.getSource());
        return episodeRepository.save(episode);
    }

    @Override
    public void hardDeleteByEpisodeId(Long episodeId) throws CustomException {
        if (!episodeRepository.existsById(episodeId)) throw new CustomException("Không thấy ID nhaaa!!");
        episodeRepository.deleteById(episodeId);

    }
}
