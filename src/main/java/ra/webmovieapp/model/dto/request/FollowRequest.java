package ra.webmovieapp.model.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FollowRequest {
    private Long seasonId;
}
