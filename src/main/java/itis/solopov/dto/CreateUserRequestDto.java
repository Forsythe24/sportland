package itis.solopov.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateUserRequestDto {

    @NotBlank(message = "Name shouldn't be blank")
    public String name;

    @Email
    @NotBlank(message = "Name shouldn't be blank")
    public String email;

    @Size(min = 8, max = 64, message = "Password should contains from 8 to 64 symbols")
    public String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
