package itis.solopov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import java.util.Objects;
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

    @Column(length = 2)
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

    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToOne
    @JoinTable(
            name = "user_sport",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id", referencedColumnName = "id")
    )
    private Sport sport;

    public Sport getSport() {
        return sport;
    }
    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(age, user.age) && Objects.equals(gender, user.gender) && Objects.equals(sport, user.sport) && Objects.equals(photo, user.photo) && Objects.equals(experience, user.experience) && Objects.equals(description, user.description) && Objects.equals(rating, user.rating) && Objects.equals(hourlyRate, user.hourlyRate) && Objects.equals(isInstructor, user.isInstructor) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, age, gender, sport, photo, experience, description, rating, hourlyRate, isInstructor, roles);
    }
}

