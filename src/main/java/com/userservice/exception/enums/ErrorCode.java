package com.userservice.exception.enums;

public enum ErrorCode {
  NOT_FOUND(404, "Not found"),

  CONFLICT(409, "Conflict");

  private final int code;
  private final String message;

  ErrorCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
