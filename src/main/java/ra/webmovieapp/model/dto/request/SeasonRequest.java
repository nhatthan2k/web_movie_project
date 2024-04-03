package ra.webmovieapp.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotEmpty(message = "Không được bỏ trống chỗ này nha!!")
    private String nickName;
    @NotEmpty(message = "Không được bỏ trống chỗ này nha!!")
    private String name;
    private String description;
    private String avatar;
    @Pattern(regexp = "^(?i)(MULTIPLE|SINGLE)$", message = "String value must be 'MULTIPLE | SINGLE'")
    private EMovieType movieType;
    @Pattern(regexp = "^(?i)(ACTIVE|INACTIVE)$", message = "String value must be 'ACTIVE | INACTIVE'")
    private EMovieStatus movieStatus;
    private LocalDate release_date;
    private Set<Day> days;
    @NotNull(message = "Không được bỏ trống chỗ này nha!!")
    private Long movieId;
}
