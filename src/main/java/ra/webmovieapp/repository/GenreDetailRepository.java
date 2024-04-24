package ra.webmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.webmovieapp.model.entity.GenreDetail;
import ra.webmovieapp.model.entity.GenreDetailId;
@Repository
public interface GenreDetailRepository extends JpaRepository<GenreDetail, GenreDetailId> {

}
