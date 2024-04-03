package ra.webmovieapp.model.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GenreDetailRequest {
    private Long genreId;
    private Long movieId;
}
