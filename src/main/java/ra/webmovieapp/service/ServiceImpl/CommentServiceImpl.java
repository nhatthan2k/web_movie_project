package ra.webmovieapp.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.request.CommentRequest;
import ra.webmovieapp.model.dto.request.CommentRequestUpdate;
import ra.webmovieapp.model.entity.Comment;
import ra.webmovieapp.model.entity.Movie;
import ra.webmovieapp.model.entity.Season;
import ra.webmovieapp.model.entity.User;
import ra.webmovieapp.repository.CommentRepository;
import ra.webmovieapp.service.CommentService;
import ra.webmovieapp.service.SeasonService;
import ra.webmovieapp.service.UserService;

import java.util.Optional;

@Service

public class CommentServiceImpl implements CommentService {
    @Autowired
    private UserService userService;
    @Autowired
    private SeasonService seasonService;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Optional<Comment> getCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public Comment addComment(CommentRequest commentRequest, Long id) throws CustomException {
        User user = userService.getUserById(id);
        Optional<Season> season = seasonService.getSeasonById(commentRequest.getSeasonId());
        if (season.isEmpty()) throw new CustomException("Phần phim này không tồn tại!!!");
        Comment comment = Comment.builder()
                .comment(commentRequest.getComment())
                .user(user)
                .season(season.get())
                .build();
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(CommentRequestUpdate commentRequestUpdate, Long id, Long commentId) throws CustomException {
        Optional<Comment> updateComment = getCommentById(commentId);
        if (updateComment.isEmpty()) throw new CustomException("Comment không tồn tại nhaaa!!");
        if (!updateComment.get().getUser().getId().equals(id)) throw new CustomException("Bạn không có quyền sửa");
        Comment comment = updateComment.get();
        comment.setComment(commentRequestUpdate.getComment());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id, Long commentId) throws CustomException {
        Optional<Comment> deleteComment = getCommentById(commentId);
        if (deleteComment.isEmpty()) throw new CustomException("Comment không tồn tại nhaaa!!");
        if (!deleteComment.get().getUser().getId().equals(id)) throw new CustomException("Bạn không thể xóa cmt này!");
        commentRepository.deleteById(commentId);
    }
}
