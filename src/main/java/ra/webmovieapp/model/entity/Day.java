package ra.webmovieapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import ra.webmovieapp.model.base.BaseModel;
import ra.webmovieapp.model.enums.EDayName;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Day extends BaseModel {
    @Enumerated(EnumType.STRING)
    private EDayName dayName;
}
