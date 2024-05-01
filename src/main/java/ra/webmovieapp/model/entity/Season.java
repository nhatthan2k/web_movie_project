package ra.webmovieapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ra.webmovieapp.model.base.BaseModel;
import ra.webmovieapp.model.enums.EMovieStatus;
import ra.webmovieapp.model.enums.EMovieType;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Season extends BaseModel {
    private String nickName;
    private String seasonName;
    private String description;
    private String avatar;
    private Boolean status;
    @Enumerated(EnumType.STRING)
    private EMovieType seasonType;
    @Enumerated(EnumType.STRING)
    private EMovieStatus seasonStatus;
    private LocalDate release_date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "film_day",
            joinColumns = @JoinColumn(name = "season_id"),
            inverseJoinColumns = @JoinColumn(name = "day_id")
    )
    private Set<Day> days;

    @OneToMany(mappedBy = "season")
    @JsonIgnore
    private List<Follow> follows;

    @OneToMany(mappedBy = "season")
    @JsonIgnore
    private List<Comment> comments;

    @OneToMany(mappedBy = "season")
    @JsonIgnore
    private List<Episode> episodes;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;
}
