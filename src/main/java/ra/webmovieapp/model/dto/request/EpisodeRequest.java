package ra.webmovieapp.model.dto.request;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EpisodeRequest {
    private Long numberEpisode;
    private String source;
    private Long seasonId;
}
