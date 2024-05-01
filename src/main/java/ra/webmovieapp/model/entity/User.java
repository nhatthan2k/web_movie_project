package ra.webmovieapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ra.webmovieapp.model.base.BaseModel;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class User extends BaseModel {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String avatar;
    private Boolean status;
    private String phone;
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Follow> follows;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Comment> comments;
}