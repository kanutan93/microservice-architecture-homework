package ru.msa.homework3.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.msa.homework3.model.dto.response.ErrorResponse;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


  @ExceptionHandler(Homework3Exception.class)
  public ErrorResponse handle(HttpServletResponse response, Homework3Exception exception) {
    int status = exception.getStatus().value();
    response.setStatus(status);
    log.error("Error: ", exception);
    return getErrorResponse(status, exception.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ErrorResponse handle(HttpServletResponse response, Exception exception) {
    int status = INTERNAL_SERVER_ERROR.value();
    response.setStatus(status);
    log.error("Error: ", exception);
    return getErrorResponse(status, exception.getMessage());
  }

  private ErrorResponse getErrorResponse(int status, String message) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(status);
    errorResponse.setMessage(message);
    return errorResponse;
  }

}
