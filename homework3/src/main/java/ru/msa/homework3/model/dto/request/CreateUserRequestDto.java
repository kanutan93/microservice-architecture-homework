package ru.msa.homework3.model.dto.request;

import lombok.Data;

@Data
public class CreateUserRequestDto {
  private String username;
  private String firstname;
  private String lastname;
  private String email;
  private String phone;
}
