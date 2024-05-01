package ra.webmovieapp.service;

import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.request.CommentRequest;
import ra.webmovieapp.model.dto.request.CommentRequestUpdate;
import ra.webmovieapp.model.entity.Comment;
import ra.webmovieapp.model.entity.Movie;

import java.util.Optional;

public interface CommentService {

    Optional<Comment> getCommentById(Long commentId);

    Comment addComment(CommentRequest commentRequest, Long id) throws CustomException;

    Comment updateComment(CommentRequestUpdate commentRequestUpdate, Long id, Long commentId) throws CustomException;

    void deleteComment(Long id, Long commentId) throws CustomException;
}
