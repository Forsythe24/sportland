package itis.solopov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "sport")
@Getter
@Setter
@ToString
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "sport")
    private Set<User> users;
    public void setUsers(Set<User> users) {
        this.users = users;
    }
}