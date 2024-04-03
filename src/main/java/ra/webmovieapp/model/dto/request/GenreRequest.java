package ra.webmovieapp.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GenreRequest {
    @NotEmpty(message = "Không được bỏ trống chỗ này nha!!")
    private String genreName;
    private String description;
    private Boolean status;
}
