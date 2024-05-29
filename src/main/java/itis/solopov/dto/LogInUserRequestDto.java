package itis.solopov.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LogInUserRequestDto {

    @Email
    @NotBlank(message = "Email shouldn't be blank")
    private String email;

    @Size(min = 8, max = 64, message = "Password should contain from 8 to 64 characters")
    @Pattern(regexp = "^(?=.*[@#$%^&+=])(?=.*[A-Z])(?=.*[a-z]).{8,}$")
    private String password;

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

