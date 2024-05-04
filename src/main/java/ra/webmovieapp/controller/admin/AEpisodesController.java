package ra.webmovieapp.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.request.EpisodeRequest;
import ra.webmovieapp.model.dto.wrapper.ResponseWrapper;
import ra.webmovieapp.model.entity.Episode;
import ra.webmovieapp.model.enums.EHttpStatus;
import ra.webmovieapp.service.EpisodeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/admin/episode")
@CrossOrigin("*")
public class AEpisodesController {
    @Autowired
    private EpisodeService episodeService;

    @GetMapping("/season/{seasonId}")
    public ResponseEntity<?> getAllEpisodesToPage(@PathVariable("seasonId") String seasonId) throws CustomException {
        try {
            Long id = Long.parseLong(seasonId);
            List<Episode> episodes = episodeService.getAllEpisodeBySeasonId(id);
            if (episodes.isEmpty()) throw new CustomException("Tập phim rỗng nhaaa");
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            episodes
                    ), HttpStatus.OK
            );
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }

    @GetMapping("/{episodeId}")
    public ResponseEntity<?> getEpisodeById(@PathVariable("episodeId") String episodeId) throws CustomException {
        try {
            Long id = Long.parseLong(episodeId);
            Optional<Episode> episode = episodeService.getEpisodeById(id);
            if (episode.isEmpty()) throw new CustomException("Tập phim không tồn tại!!");
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            episode.get()
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }

    @PostMapping
    public ResponseEntity<?> createEpisode(@RequestBody EpisodeRequest episodeRequest) throws CustomException {
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        episodeService.add(episodeRequest)
                ),
                HttpStatus.CREATED);
    }

    @PutMapping("/{episodeId}")
    public ResponseEntity<?> updateEpisode(
            @PathVariable("episodeId") String updateEpisodeId,
            @RequestBody EpisodeRequest episodeRequest
    ) throws CustomException {
        try {
            Long id = Long.parseLong(updateEpisodeId);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            episodeService.updateEpisode(id, episodeRequest)
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }

    @PutMapping("/{episodeId}/status")
    public ResponseEntity<?> hardDeleteEpisodeById(@PathVariable("episodeId") String EpisodeId) throws CustomException {
        try {
            Long id = Long.parseLong(EpisodeId);
            episodeService.changeStatus(id);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            "Delete episode successfully"
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }
}


