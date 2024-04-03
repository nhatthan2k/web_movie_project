package ra.webmovieapp.model.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GenreRequest {
    private String genreName;
    private String description;
    private Boolean status;
}
