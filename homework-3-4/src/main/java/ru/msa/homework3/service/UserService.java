package ru.msa.homework3.service;

import ru.msa.homework3.model.dto.request.CreateUserRequestDto;
import ru.msa.homework3.model.dto.request.UpdateUserRequestDto;
import ru.msa.homework3.model.dto.response.UserResponse;

public interface UserService {

  UserResponse getUser(Long id);

  Long createUser(CreateUserRequestDto createUserRequestDto);

  void deleteUser(Long id);

  void updateUser(Long id, UpdateUserRequestDto updateUserRequestDto);
}
