package ra.webmovieapp.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DayRequest {
    @NotEmpty(message = "Không được bỏ trống chỗ này nha!!")
    private String dayName;
}
