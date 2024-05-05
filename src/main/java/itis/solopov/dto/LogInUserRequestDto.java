package itis.solopov.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LogInUserRequestDto {

    @Email
    @NotBlank(message = "Email shouldn't be blank")
    public String email;

    public String password;

    @NotBlank
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

