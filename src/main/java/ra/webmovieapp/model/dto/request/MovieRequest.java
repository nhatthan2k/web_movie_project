package ra.webmovieapp.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import ra.webmovieapp.model.entity.Genre;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieRequest {
    @NotEmpty(message = "Không được bỏ trống chỗ này nha!!")
    private String movieName;
    private String poster;
    private String description;
    private Boolean status;
    private Set<GenreId> genreId;
}
