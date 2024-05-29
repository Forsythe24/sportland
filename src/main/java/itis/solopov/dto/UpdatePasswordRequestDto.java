package itis.solopov.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UpdatePasswordRequestDto {

    public String email;

    @Size(min = 8, max = 64, message = "Password should contain from 8 to 64 characters")
    @Pattern(regexp = "^(?=.*[@#$%^&+=])(?=.*[A-Z])(?=.*[a-z]).{8,}$")
    public String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
