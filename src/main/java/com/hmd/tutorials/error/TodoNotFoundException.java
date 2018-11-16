package com.hmd.tutorials.error;

import org.springframework.http.HttpStatus;

public class TodoNotFoundException extends ApiBaseException {

  public TodoNotFoundException(String message) {
    super(message);
  }

  public HttpStatus getStatusCode() {
    return HttpStatus.NOT_FOUND;
  }
}
