package ra.webmovieapp.service;

import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.request.FollowRequest;
import ra.webmovieapp.model.entity.Follow;
import ra.webmovieapp.model.entity.Season;

import java.util.List;

public interface FollowService {
    List<Season> getAllSeasonByUserId(Long userId);

    Follow addSeasonToFollow(FollowRequest followRequest, Long userId) throws CustomException;

    void deteleSeasonToFollow(Long seasonId, Long userId);
}

