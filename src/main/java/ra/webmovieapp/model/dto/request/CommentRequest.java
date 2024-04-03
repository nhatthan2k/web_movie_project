package ra.webmovieapp.model.dto.request;

import lombok.*;
import ra.webmovieapp.model.entity.Season;
import ra.webmovieapp.model.entity.User;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentRequest {
    private String comment;
    private Double rate;
    private Long seasonId;
}
