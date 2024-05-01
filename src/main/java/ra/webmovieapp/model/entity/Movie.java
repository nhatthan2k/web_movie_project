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
public class Movie extends BaseModel {
    private String movieName;
    private String poster;
    private String description;
    private Boolean status;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<Season> seasons;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<GenreDetail> genreDetails;
}
