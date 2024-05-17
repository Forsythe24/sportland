package itis.solopov.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateUserRequestDto {

    @NotBlank(message = "Name should contain at least 2 characters")
    @Size(min = 2)
    private String name;

    @Email
    @NotBlank(message = "Email shouldn't be blank")
    private String email;

    @Size(min = 8, max = 64, message = "Password should contains from 8 to 64 symbols")
    private String password;


    @NotBlank
    private Integer age;

    @NotBlank
    private String gender;

    public String getEmail() {
        return email;
    }

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

    public void setEmail(String email) {
        this.email = email;
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
}
