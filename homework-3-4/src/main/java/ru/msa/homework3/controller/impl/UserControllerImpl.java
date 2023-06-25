package ru.msa.homework3.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.msa.homework3.controller.UserController;
import ru.msa.homework3.model.dto.request.CreateUserRequestDto;
import ru.msa.homework3.model.dto.request.UpdateUserRequestDto;
import ru.msa.homework3.model.dto.response.UserResponse;
import ru.msa.homework3.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

  private final UserService userService;


  @Override
  public ResponseEntity<Long> createUser(CreateUserRequestDto createUserRequestDto) {
    Long userId = userService.createUser(createUserRequestDto);
    return ResponseEntity.ok(userId);
  }

  @Override
  public ResponseEntity<UserResponse> getUser(Long userId) {
    UserResponse userResponse = userService.getUser(userId);
    return ResponseEntity.ok(userResponse);
  }

  @Override
  public ResponseEntity<Void> deleteUser(Long userId) {
    userService.deleteUser(userId);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<Void> updateUser(Long userId, UpdateUserRequestDto updateUserRequestDto) {
    userService.updateUser(userId, updateUserRequestDto);
    return ResponseEntity.ok().build();
  }
}
