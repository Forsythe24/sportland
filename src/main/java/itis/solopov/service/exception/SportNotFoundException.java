package itis.solopov.service.exception;

public class SportNotFoundException extends RuntimeException {

    public SportNotFoundException(String message) {
        super(message);
    }
}