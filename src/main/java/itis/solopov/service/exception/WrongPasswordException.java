package itis.solopov.service.exception;

public class WrongPasswordException extends RuntimeException {

    private final String message;
    public WrongPasswordException(String message) {
        this.message = message;
    }
}
