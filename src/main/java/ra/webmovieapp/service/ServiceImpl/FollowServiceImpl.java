package ra.webmovieapp.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.request.FollowRequest;
import ra.webmovieapp.model.entity.Follow;
import ra.webmovieapp.model.entity.Season;
import ra.webmovieapp.model.entity.User;
import ra.webmovieapp.repository.FollowRepository;
import ra.webmovieapp.service.FollowService;
import ra.webmovieapp.service.SeasonService;
import ra.webmovieapp.service.UserService;

import java.util.List;
import java.util.Optional;

@Service

public class FollowServiceImpl implements FollowService {
    @Autowired
    private UserService userService;
    @Autowired
    private SeasonService seasonService;

    @Autowired
    private FollowRepository followRepository;

    @Override
    public List<Season> getAllSeasonByUserId(Long userId) {
        return followRepository.findSeasonByUserId(userId);
    }

    @Override
    public Follow addSeasonToFollow(FollowRequest followRequest, Long userId) throws CustomException {
        User user = userService.getUserById(userId);
        Optional<Season> season = seasonService.getSeasonById(followRequest.getSeasonId());
        if (season.isEmpty()) throw new CustomException("Phần phim này không tồn tại!!!");
        if (followRepository.existsBySeason(season.get())) throw new CustomException("Đã follow");
        Follow follow = Follow.builder()
                .season(season.get())
                .user(user)
                .build();
        return followRepository.save(follow);
    }

    @Override
    public void deteleSeasonToFollow(Long seasonId, Long userId) {
        followRepository.deleteBySeasonIdAndUserId(seasonId, userId);
    }
}
