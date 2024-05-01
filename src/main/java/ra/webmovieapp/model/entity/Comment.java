package ra.webmovieapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import ra.webmovieapp.model.base.BaseModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Comment extends BaseModel {
    private String comment;
    private Double rate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "season_id", referencedColumnName = "id")
    private Season season;
}
