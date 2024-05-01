package ra.webmovieapp.model.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GenreDetailId implements Serializable {
    private Genre genre;
    private Movie movie;
}
