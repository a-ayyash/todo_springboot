package com.hmd.tutorials.error;

import org.springframework.http.HttpStatus;

public class TodoConflictException extends ApiBaseException {
  public TodoConflictException(String message) {
    super(message);
  }

  public HttpStatus getStatusCode() {
    return HttpStatus.CONFLICT;
  }
}
