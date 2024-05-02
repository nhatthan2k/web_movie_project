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
    private String seasonName;
    private String description;
    private String avatar;
    private Boolean status;
    @Pattern(regexp = "^(?i)(MULTIPLE|SINGLE)$", message = "String value must be 'MULTIPLE | SINGLE'")
    private String seasonType;
    @Pattern(regexp = "^(?i)(COMING|SHOWING|COMPLETE)$", message = "String value must be 'COMING |SHOWING|COMPLETE'")
    private String seasonStatus;
    private LocalDate release_date;
    private Set<String> days;
    @NotNull(message = "Không được bỏ trống chỗ này nha!!")
    private Long movieId;
}
