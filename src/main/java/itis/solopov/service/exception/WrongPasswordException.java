package itis.solopov.service.exception;

public class WrongPasswordException extends Exception {

    private String message;
    public WrongPasswordException(String message) {
        this.message = message;
    }
}
