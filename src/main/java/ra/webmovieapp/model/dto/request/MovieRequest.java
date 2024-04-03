package ra.webmovieapp.model.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieRequest {
    private String name;
    private String poster;
    private String description;
}
