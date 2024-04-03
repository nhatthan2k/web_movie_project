package ra.webmovieapp.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GenreDetailRequest {
    @NotNull(message = "Không được bỏ trống chỗ này nha!!")
    private Long genreId;
    @NotNull(message = "Không được bỏ trống chỗ này nha!!")
    private Long movieId;
}
