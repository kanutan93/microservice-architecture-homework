package ru.msa.homework3.exception;

import org.springframework.http.HttpStatus;

public class Homework3Exception extends RuntimeException{
  HttpStatus status;

  public Homework3Exception(Homework3Error errorMessage, HttpStatus status) {
    super(errorMessage.name());
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
