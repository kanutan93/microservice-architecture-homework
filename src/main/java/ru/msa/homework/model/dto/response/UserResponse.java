package ru.msa.homework.model.dto.response;

import lombok.Data;

@Data
public class UserResponse {
  private Long id;
  private String username;
  private String firstname;
  private String lastname;
  private String email;
  private String phone;
}
