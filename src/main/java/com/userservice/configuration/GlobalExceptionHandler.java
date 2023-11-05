package com.userservice.configuration;

import static com.userservice.exception.enums.ErrorCode.CONFLICT;
import static com.userservice.exception.enums.ErrorCode.NOT_FOUND;

import com.userservice.exception.DuplicateUserException;
import com.userservice.exception.ErrorMessage;
import com.userservice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<ErrorMessage> handleNotFoundException(UserNotFoundException ex) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ErrorMessage(NOT_FOUND.getCode(), ex.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<ErrorMessage> handleUserIsAlreadyExist(DuplicateUserException ex) {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(new ErrorMessage(CONFLICT.getCode(), ex.getMessage()));
  }
}
