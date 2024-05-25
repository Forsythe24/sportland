package itis.solopov.dto;

import org.springframework.http.HttpStatus;

public class ErrorResponseDto {
    private final String message;
    private final HttpStatus status;

    public ErrorResponseDto(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
