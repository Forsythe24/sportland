package itis.solopov.service.exception;

public class SportNotFoundException extends RuntimeException {

    private final String message;
    public SportNotFoundException(String message) {
        this.message = message;
    }
}