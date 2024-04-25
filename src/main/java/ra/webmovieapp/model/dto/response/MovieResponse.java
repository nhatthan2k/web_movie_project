package ra.webmovieapp.model.dto.response;

import lombok.*;
import ra.webmovieapp.model.entity.Genre;
import ra.webmovieapp.model.entity.Movie;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private Movie movie;
    private List<Genre> Genres;
}
