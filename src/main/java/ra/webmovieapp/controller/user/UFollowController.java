package ra.webmovieapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.request.FollowRequest;
import ra.webmovieapp.model.dto.wrapper.ResponseWrapper;
import ra.webmovieapp.model.entity.Season;
import ra.webmovieapp.model.enums.EHttpStatus;
import ra.webmovieapp.security.UserDetail.UserLoggedIn;
import ra.webmovieapp.service.FollowService;

@RestController
@RequestMapping("/v1/user/follow")
@CrossOrigin("*")
public class UFollowController {
    @Autowired
    private FollowService followService;
    @Autowired
    private UserLoggedIn userLoggedIn;

    @GetMapping
    public ResponseEntity<?> getFollowFilmById() throws CustomException {
            Long id = userLoggedIn.getUserLoggedIn().getId();
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            followService.getAllSeasonByUserId(id)
                    ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addSeasonToFollow(@RequestBody FollowRequest followRequest) throws CustomException {
        Long id = userLoggedIn.getUserLoggedIn().getId();
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        followService.addSeasonToFollow(followRequest, id)
                ), HttpStatus.CREATED);
    }

    @DeleteMapping("/{seasonId}")
    public ResponseEntity<?> deleteSeasonToFollow(@PathVariable("seasonId") String seasonId) throws CustomException {
        Long idLogin = userLoggedIn.getUserLoggedIn().getId();
        try {
            Long seasonIdReq = Long.parseLong(seasonId);
            followService.deteleSeasonToFollow(seasonIdReq, idLogin);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            "Delete class successfully"
                    ), HttpStatus.OK);
        } catch (NumberFormatException e){
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }
}
