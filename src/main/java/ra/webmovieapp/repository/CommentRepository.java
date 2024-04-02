package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.webmovieapp.model.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
