package ra.webmovieapp.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ra.webmovieapp.model.entity.Season;
import ra.webmovieapp.model.entity.User;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentRequest {
    @NotEmpty(message = "Không được bỏ trống chỗ này nha!!")
    private String comment;
    @NotNull(message = "Không được bỏ trống chỗ này nha!!")
    private Double rate;
    @NotNull(message = "Không được bỏ trống chỗ này nha!!")
    private Long seasonId;
}
