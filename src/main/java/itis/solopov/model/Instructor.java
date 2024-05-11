//package itis.solopov.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//import org.hibernate.annotations.ColumnDefault;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "instructor")
//@Setter
//@Getter
//@ToString
//public class Instructor {
//
//    @Id
//    public String id;
//
//    @Column(length = 50, nullable = false)
//    public String name;
//
//    @Column(length = 2)
//    public Integer age;
//
//    @Column(length = 1)
//    public String gender;
//    @Column
//    public String photo;
//    @ColumnDefault("0.0")
//    public Float rating;
//
//    @Column(name = "sport_id")
//    public Integer sportId;
//
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @JsonIgnore
//    @ManyToOne
//    @JoinTable(
//            name = "user_sport",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "sport_id", referencedColumnName = "id")
//    )
//    private Sport sport;
//
//    public Sport getSport() {
//        return sport;
//    }
//    public void setSport(Sport sport) {
//        this.sport = sport;
//    }

//    public Instructor(Long id, String name, Integer age, String gender, String photo, Float rating) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.gender = gender;
//        this.photo = photo;
//        this.rating = rating;
//
//    }
//}
