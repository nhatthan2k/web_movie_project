package ra.webmovieapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import ra.webmovieapp.model.base.BaseModel;
import ra.webmovieapp.model.enums.ERoleName;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Role extends BaseModel {
    @Enumerated(EnumType.STRING)
    private ERoleName roleName;
}
