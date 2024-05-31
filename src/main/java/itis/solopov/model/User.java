package itis.solopov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@ToString
public class User {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 64, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 2, nullable = false)
    private Integer age;

    @Column(length = 1)
    private String gender;

    @Column(name = "sport_name")
    private String sportName;

    @Column
    private String photo;

    @Column
    private String experience;

    @Column
    private String description;

    @ColumnDefault("0.0")
    private Float rating;

    @Column(name = "number_of_ratings")
    @ColumnDefault("0")
    private Integer numberOfRatings;

    @Column(name = "hourly_rate")
    @ColumnDefault("0.0")
    private Float hourlyRate;

    @Column(name = "is_instructor")
    @ColumnDefault("false")
    private Boolean isInstructor;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(
            name = "user_sport",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id", referencedColumnName = "id")
    )
    private Sport sport;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "user")
    private Set<Chat> chats;

    public Sport getSport() {
        return sport;
    }
    public void setSport(Sport sport) {
        this.sport = sport;
    }
}

