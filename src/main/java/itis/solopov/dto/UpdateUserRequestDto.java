package itis.solopov.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UpdateUserRequestDto {

    @NotBlank
    private String id;

    @NotBlank(message = "Name should contain at least 2 characters")
    @Size(min = 2)
    private String name;

    @Size(min = 8, max = 64, message = "Password should contain from 8 to 64 characters")
    @Pattern(regexp = "^(?=.*[@#$%^&+=])(?=.*[A-Z])(?=.*[a-z]).{8,}$")
    private String password;


    @NotBlank
    private Integer age;

    @NotBlank
    private String gender;

    private String sportName;

    private String photo;


    private String experience;

    private String description;

    private Float rating;
    private Integer numberOfRatings;
    private Float hourlyRate;

    private Boolean isInstructor;

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Boolean getIsInstructor() {
        return isInstructor;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public String getSport() {
        return sportName;
    }

    public String getPhoto() {
        return photo;
    }

    public String getExperience() {
        return experience;
    }

    public String getDescription() {
        return description;
    }

    public Float getRating() {
        return rating;
    }

    public String getSportName() {
        return sportName;
    }

    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    public Float getHourlyRate() {
        return hourlyRate;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setIsInstructor(Boolean isInstructor) {
        this.isInstructor = isInstructor;
    }

    public void setNumberOfRatings(Integer numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public void setHourlyRate(Float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
