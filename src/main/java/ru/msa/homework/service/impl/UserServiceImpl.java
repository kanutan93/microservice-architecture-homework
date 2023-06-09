package ru.msa.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.msa.homework.exception.MsaHomeworkException;
import ru.msa.homework.mapper.UserMapper;
import ru.msa.homework.model.dao.UserEntity;
import ru.msa.homework.model.dto.request.CreateUserRequestDto;
import ru.msa.homework.model.dto.request.UpdateUserRequestDto;
import ru.msa.homework.model.dto.response.UserResponse;
import ru.msa.homework.repository.UserRepository;
import ru.msa.homework.service.UserService;

import static org.springframework.http.HttpStatus.*;
import static ru.msa.homework.exception.MsaHomeworkError.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  @Transactional(readOnly = true)
  public UserResponse getUser(Long id) {
    log.info("Trying to get user by id: {}", id);

    UserEntity userEntity = userRepository
        .findById(id)
        .orElseThrow(() -> new MsaHomeworkException(USER_NOT_FOUND, NOT_FOUND));

    UserResponse userResponse = userMapper.toUserResponse(userEntity);
    log.info("User: {} received succesfully", userResponse);
    return userResponse;
  }

  @Override
  @Transactional
  public Long createUser(CreateUserRequestDto createUserRequestDto) {
    log.info("Trying to create user by request: {}", createUserRequestDto);

    UserEntity userEntity = userMapper.toUserEntity(createUserRequestDto);
    UserEntity savedUserEntity = userRepository.save(userEntity);

    log.info("Successfully saved UserEntity: {}", savedUserEntity);

    return savedUserEntity.getId();
  }

  @Override
  @Transactional
  public void deleteUser(Long id) {
    log.info("Trying to delete user by id: {}", id);

    userRepository.deleteById(id);

    log.info("Successfully deleted user by id: {}", id);
  }

  @Override
  @Transactional
  public void updateUser(Long id, UpdateUserRequestDto updateUserRequestDto) {
    log.info("Trying to update user by id: {} and request: {}", id, updateUserRequestDto);

    UserEntity userEntity = userMapper.toUserEntity(id, updateUserRequestDto);
    userRepository.save(userEntity);

    log.info("Successfully updated UserEntity: {}", userEntity);
  }
}
