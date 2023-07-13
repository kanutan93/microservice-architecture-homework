package ru.msa.homework.service;

import ru.msa.homework.model.dto.request.CreateUserRequestDto;
import ru.msa.homework.model.dto.request.UpdateUserRequestDto;
import ru.msa.homework.model.dto.response.UserResponse;

public interface UserService {

  UserResponse getUser(Long id);

  Long createUser(CreateUserRequestDto createUserRequestDto);

  void deleteUser(Long id);

  void updateUser(Long id, UpdateUserRequestDto updateUserRequestDto);
}
