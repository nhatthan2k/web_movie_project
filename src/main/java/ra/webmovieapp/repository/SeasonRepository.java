package ra.webmovieapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.webmovieapp.model.entity.Day;
import ra.webmovieapp.model.entity.Movie;
import ra.webmovieapp.model.entity.Season;
import ra.webmovieapp.model.enums.EMovieStatus;
import ra.webmovieapp.model.enums.EMovieType;

import java.util.List;
import java.util.Set;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    Page<Season> findAll(Pageable pageable);
    Page<Season> findAllByMovieStatus(EMovieStatus movieStatus, Pageable pageable);
    Page<Season> searchBySeasonNameOrNickNameContainingIgnoreCase(String name, String nickName, Pageable pageable);
    @Query("select s from Season s join s.movie m join m.genreDetails g where g.genre.id = :genreId")
    Page<Season> findAllByGenreId(Long genreId, Pageable pageable);

    Page<Season> findAllByMovieType(EMovieType movieType, Pageable pageable);
    @Query("select s from Season s join Day d where d.id = :dayId")
    Page<Season> findAllByDayId(Long dayId, Pageable pageable);

    List<Season> findAllByMovie(Movie movie);
}
