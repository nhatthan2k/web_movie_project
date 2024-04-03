package ra.webmovieapp.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ra.webmovieapp.model.entity.*;
import ra.webmovieapp.model.enums.EMovieStatus;
import ra.webmovieapp.model.enums.EMovieType;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SeasonRequest {
    private String nickName;
    private String name;
    private String description;
    private String avatar;
    private EMovieType movieType;
    private EMovieStatus movieStatus;
    private LocalDate release_date;
    private Set<Day> days;
    private Long movieId;
}
