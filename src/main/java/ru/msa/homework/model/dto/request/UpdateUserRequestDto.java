package ru.msa.homework.model.dto.request;

import lombok.Data;

@Data
public class UpdateUserRequestDto {
  private String username;
  private String firstname;
  private String lastname;
  private String email;
  private String phone;
}
