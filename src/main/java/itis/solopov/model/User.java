package itis.solopov.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique = true, nullable = false)
    public String email;

    @Column(length = 64, nullable = false)
    public String password;

    @Column(length = 50, nullable = false)
    public String name;

    @Column(length = 2)
    public Integer age;

    @Column(length = 1)
    public String gender;
    @Column
    public String sport;

    @Column
    public String photo;

    @Column
    public String experience;

    @Column
    public String description;

    @ColumnDefault("0.0")
    public Float rating;

    @ColumnDefault("0")
    public Integer numberOfRatings;

    @Column
    public Float hourlyRate;

    @ColumnDefault("false")
    public String isInstructor;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles;

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

