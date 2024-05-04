package ra.webmovieapp.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EpisodeRequest {
    @NotNull(message = "Không được bỏ trống chỗ này nha!!")
    private Long numberEpisode;
    private String source;
    private Boolean status;
    @NotNull(message = "Không được bỏ trống chỗ này nha!!")
    private Long seasonId;
}
