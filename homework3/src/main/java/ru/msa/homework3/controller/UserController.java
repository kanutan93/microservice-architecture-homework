package ru.msa.homework3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.msa.homework3.model.dto.request.CreateUserRequestDto;
import ru.msa.homework3.model.dto.request.UpdateUserRequestDto;
import ru.msa.homework3.model.dto.response.UserResponse;

@RequestMapping("/user")
public interface UserController {

  @PostMapping
  ResponseEntity<Long> createUser(@RequestBody CreateUserRequestDto createUserRequestDto);

  @GetMapping("/{userId}")
  ResponseEntity<UserResponse> getUser(@PathVariable Long userId);

  @DeleteMapping("/{userId}")
  ResponseEntity<Void> deleteUser(@PathVariable Long userId);

  @PutMapping("/{userId}")
  ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequestDto updateUserRequestDto);
}
