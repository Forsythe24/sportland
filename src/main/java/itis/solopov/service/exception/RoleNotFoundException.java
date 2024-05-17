package itis.solopov.service.exception;

public class RoleNotFoundException extends RuntimeException {

    private final String message;
    public RoleNotFoundException(String message) {
        this.message = message;
    }
}
