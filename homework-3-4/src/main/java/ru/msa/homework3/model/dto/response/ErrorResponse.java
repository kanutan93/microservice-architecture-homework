package ru.msa.homework3.model.dto.response;

import lombok.Data;

@Data
public class ErrorResponse {
  private Integer code;
  private String message;
}
