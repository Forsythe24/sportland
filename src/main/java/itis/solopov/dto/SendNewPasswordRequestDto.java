package itis.solopov.dto;

import javax.validation.constraints.Email;

public class SendNewPasswordRequestDto {
    @Email
    private String email;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
