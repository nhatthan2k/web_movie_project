package ra.webmovieapp.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieRequest {
    @NotEmpty(message = "Không được bỏ trống chỗ này nha!!")
    private String name;
    private String poster;
    private String description;
}
