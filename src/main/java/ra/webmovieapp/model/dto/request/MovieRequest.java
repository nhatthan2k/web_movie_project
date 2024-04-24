package ra.webmovieapp.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import ra.webmovieapp.model.entity.Genre;

import java.util.List;

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
    private List<GenreId> genreId;
}
