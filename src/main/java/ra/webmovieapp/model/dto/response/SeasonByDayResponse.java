package ra.webmovieapp.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ra.webmovieapp.model.entity.Season;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeasonByDayResponse {
    private String dayName;
    private List<Season> season;
}
