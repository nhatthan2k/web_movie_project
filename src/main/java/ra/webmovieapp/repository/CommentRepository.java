package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.webmovieapp.model.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
