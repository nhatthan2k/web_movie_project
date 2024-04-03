package ra.webmovieapp.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FollowRequest {
    @NotNull(message = "Không được bỏ trống chỗ này nha!!")
    private Long seasonId;
}
