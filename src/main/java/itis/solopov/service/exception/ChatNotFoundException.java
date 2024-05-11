package itis.solopov.service.exception;

public class ChatNotFoundException extends RuntimeException {

    private final String message;
    public ChatNotFoundException(String message) {
        this.message = message;
    }
}
