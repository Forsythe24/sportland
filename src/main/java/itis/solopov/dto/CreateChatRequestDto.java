package itis.solopov.dto;

import javax.validation.constraints.NotBlank;

public class CreateChatRequestDto {
    @NotBlank
    private String id;

    @NotBlank
    private String userId;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
