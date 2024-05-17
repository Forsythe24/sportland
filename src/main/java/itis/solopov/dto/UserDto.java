package itis.solopov.dto;


public class UserDto {

    private String id;

    private String name;

    private Integer age;

    private String gender;

    private String sportName;

    private String photo;

    private String experience;

    private String description;

    private Float rating;

    private Integer numberOfRatings;

    private Float hourlyRate;

    private Boolean isInstructor;

    public String getId() {
        return id;
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

    public String getSportName() {
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

    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    public Float getHourlyRate() {
        return hourlyRate;
    }

    public Boolean getIsInstructor() {
        return isInstructor;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setNumberOfRatings(Integer numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public void setHourlyRate(Float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setIsInstructor(Boolean isInstructor) {
        this.isInstructor = isInstructor;
    }
}
