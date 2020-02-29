package com.drh.svc.users.handler;

import com.drh.svc.users.exception.DrhException;
import com.drh.svc.users.model.DrhError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class DrhErrorHandler {

  @ExceptionHandler({DrhException.class})
  @ResponseBody
  public ResponseEntity<DrhError> handleDrhException(DrhException e) {
    return new ResponseEntity<>(DrhError.create(e.getMessage()), e.getStatus());
  }
}
