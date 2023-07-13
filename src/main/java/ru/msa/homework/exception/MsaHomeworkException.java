package ru.msa.homework.exception;

import org.springframework.http.HttpStatus;

public class MsaHomeworkException extends RuntimeException{
  HttpStatus status;

  public MsaHomeworkException(MsaHomeworkError errorMessage, HttpStatus status) {
    super(errorMessage.name());
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
