package ra.webmovieapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.webmovieapp.model.entity.GenreDetail;
import ra.webmovieapp.model.entity.GenreDetailId;
import ra.webmovieapp.model.entity.Movie;

import java.util.List;

@Repository
public interface GenreDetailRepository extends JpaRepository<GenreDetail, GenreDetailId> {
    List<GenreDetail> findByMovie(Movie movie);
}