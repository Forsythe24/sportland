package itis.solopov.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

  @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(
      Exception ex) {

    String message = "Exception: " + ex.getMessage();
    LOGGER.warn(message);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
  }
}