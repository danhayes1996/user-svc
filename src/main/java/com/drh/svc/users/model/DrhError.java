package com.drh.svc.users.model;

import lombok.Getter;

@Getter
public class DrhError {

  private String code;
  private String message;

  public static DrhError create(String errorMessage) {
    String[] parts = errorMessage.split(":");
    DrhError error = new DrhError();
    if (parts.length > 1) {
      error.code = parts[0];
      error.message = parts[1];
    } else {
      error.message = errorMessage;
    }
    return error;
  }
}
