package ra.webmovieapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.request.CommentRequest;
import ra.webmovieapp.model.dto.request.CommentRequestUpdate;
import ra.webmovieapp.model.dto.wrapper.ResponseWrapper;
import ra.webmovieapp.model.enums.EHttpStatus;
import ra.webmovieapp.security.UserDetail.UserLoggedIn;
import ra.webmovieapp.service.CommentService;

@RestController
@RequestMapping("/v1/user/comment")
@CrossOrigin("*")
public class UCommentController {
    @Autowired
    private UserLoggedIn userLoggedIn;
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentRequest commentRequest) throws CustomException {
        Long id = userLoggedIn.getUserLoggedIn().getId();
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        commentService.addComment(commentRequest, id)
                ), HttpStatus.OK);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("commentId") String commentId,
                                           @RequestBody CommentRequestUpdate commentRequestUpdate) throws CustomException{
        Long userId = userLoggedIn.getUserLoggedIn().getId();
        try {
            Long id = Long.parseLong(commentId);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            commentService.updateComment(commentRequestUpdate, userId, id)
                    ), HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw new CustomException("Sai định dạng ID rồi nhaa!!");
        }
    }


}
