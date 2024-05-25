package itis.solopov.handler;

import itis.solopov.dto.ErrorResponseDto;
import itis.solopov.service.exception.*;
import kotlin.io.AccessDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.security.auth.message.AuthException;


@RestControllerAdvice
public class ExceptionApiHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionApiHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(
            Exception ex) {

        String message = "Exception: " + ex.getMessage();
        LOGGER.warn(message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> usernameNotFound(UserNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(ex.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorResponseDto> wrongPassword(WrongPasswordException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseDto(ex.getMessage(), HttpStatus.UNAUTHORIZED));
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponseDto> auth(AuthException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> roleNotFound(RoleNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDto(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> userAlreadyExists(UserAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponseDto(ex.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(ChatAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> chatAlreadyExists(ChatAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponseDto(ex.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(ChatNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> chatNotFound(ChatNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(ex.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(SportNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> sportNotFound(ChatNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(ex.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> methodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ErrorResponseDto(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED));
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ErrorResponseDto> serviceUnavailable(ServiceUnavailableException ex) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ErrorResponseDto(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE));
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponseDto> pageNotFound() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto("The page you were looking for was not found", HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> accessDenied() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseDto("Access to this resource is denied due to lack of authority", HttpStatus.UNAUTHORIZED));
    }
}