package itis.solopov.dto;

import javax.validation.constraints.NotBlank;

public class VerifyCredentialsRequestDto {
    @NotBlank
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
