package ra.webmovieapp.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.wrapper.ResponseWrapper;
import ra.webmovieapp.model.entity.Episode;
import ra.webmovieapp.model.enums.EHttpStatus;
import ra.webmovieapp.service.EpisodeService;

import java.util.Optional;

@RestController
@RequestMapping("/v1/admin/episode")
@CrossOrigin("*")
public class AEpisodesController {
    @Autowired
    private EpisodeService episodeService;

    @GetMapping("/season/{seasonId}")
    public ResponseEntity<?> getAllEpisodesToPage(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "number", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "order") String order,
            @PathVariable("seasonId") String seasonId
    ) throws CustomException {
        Pageable pageable;
        if (order.equals("asc")) pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        else pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        try {
            Long id = Long.parseLong(seasonId);
            Page<Episode> episodes = episodeService.getAllEpisodeBySeasonId(pageable, id);
            if (episodes.getContent().isEmpty()) throw new CustomException("Tập phim rỗng nhaaa");
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            episodes.getContent()
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
    public ResponseEntity<?> createEpisode(@RequestBody Episode episodeReq) throws CustomException {
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        episodeService.save(episodeReq)
                ),
                HttpStatus.CREATED);
    }

    @PutMapping("/{episodeId}")
    public ResponseEntity<?> updateEpisode(
            @PathVariable("episodeId") String updateEpisodeId,
            @RequestBody Episode episodeReq
    ) throws CustomException {
        try {
            Long id = Long.parseLong(updateEpisodeId);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            episodeService.updateEpisode(id, episodeReq)
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }

    @DeleteMapping("/delete/{episodeId}")
    public ResponseEntity<?> hardDeleteEpisodeById(@PathVariable("episodeId") String deleteEpisodeId) throws CustomException {
        try {
            Long id = Long.parseLong(deleteEpisodeId);
            episodeService.hardDeleteByEpisodeId(id);
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


