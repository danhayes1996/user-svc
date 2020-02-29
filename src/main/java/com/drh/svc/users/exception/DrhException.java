package com.drh.svc.users.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class DrhException extends RuntimeException {

  @Getter
  private final HttpStatus status;

  public DrhException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public DrhException(String message, Exception cause) {
    super(message, cause);
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
  }
}
