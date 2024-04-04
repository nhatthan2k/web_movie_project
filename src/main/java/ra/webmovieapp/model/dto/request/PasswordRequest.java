package ra.webmovieapp.model.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PasswordRequest {
    private String oldPass;
    private String newPass;
    private String confirmNewPass;
}
