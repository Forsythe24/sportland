package itis.solopov.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateUserRequestDto {

    @NotBlank(message = "Name shouldn't be blank")
    @Size(min = 2)
    public String name;

    @Email
    @NotBlank(message = "Email shouldn't be blank")
    public String email;

    @Size(min = 8, max = 64, message = "Password should contains from 8 to 64 symbols")
    public String password;


    @NotBlank
    public Integer age;

    @NotBlank
    public String gender;

    @NotBlank
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
