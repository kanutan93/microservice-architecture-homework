package ru.msa.homework.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.msa.homework.model.dto.response.ErrorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
  private final ErrorRateGauge errorRateCounter;

  @ExceptionHandler(MsaHomeworkException.class)
  public ErrorResponse handle(HttpServletRequest request, HttpServletResponse response, MsaHomeworkException exception) {
    int status = exception.getStatus().value();
    response.setStatus(status);
    log.error("Error: ", exception);
    errorRateCounter.increment(request.getMethod(), request.getRequestURI());
    return getErrorResponse(status, exception.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ErrorResponse handle(HttpServletRequest request, HttpServletResponse response, Exception exception) {
    int status = INTERNAL_SERVER_ERROR.value();
    response.setStatus(status);
    log.error("Error: ", exception);
    errorRateCounter.increment(request.getMethod(), request.getRequestURI());
    return getErrorResponse(status, exception.getMessage());
  }

  private ErrorResponse getErrorResponse(int status, String message) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(status);
    errorResponse.setMessage(message);
    return errorResponse;
  }

}
