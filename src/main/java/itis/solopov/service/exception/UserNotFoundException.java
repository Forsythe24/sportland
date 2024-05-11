package itis.solopov.service.exception;

public class UserNotFoundException extends RuntimeException {

    private final String message;
    public UserNotFoundException(String message) {
        this.message = message;
    }
}
