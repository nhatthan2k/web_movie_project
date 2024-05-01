package ra.webmovieapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import ra.webmovieapp.model.base.BaseModel;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Genre extends BaseModel {
    private String genreName;
    private String description;
    private Boolean status;

    @OneToMany(mappedBy = "genre")
    @JsonIgnore
    private List<GenreDetail> genreDetails;
}
