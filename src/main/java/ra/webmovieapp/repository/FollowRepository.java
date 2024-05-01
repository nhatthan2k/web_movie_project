package ra.webmovieapp.repository;

import org.springframework.boot.availability.LivenessState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.webmovieapp.model.entity.Follow;
import ra.webmovieapp.model.entity.Season;
import ra.webmovieapp.model.entity.User;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Query("select f.season from Follow f where f.user.id = :userId")
    List<Season> findSeasonByUserId(Long userId);

    @Query("delete from Follow f where f.user.id = :userId and f.season.id = :seasonId")
    void deleteBySeasonIdAndUserId(Long seasonId, Long userId);

    boolean existsBySeason(Season season);
}
