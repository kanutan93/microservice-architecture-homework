package ru.msa.homework3.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.msa.homework3.exception.Homework3Error;
import ru.msa.homework3.exception.Homework3Exception;
import ru.msa.homework3.mapper.UserMapper;
import ru.msa.homework3.model.dao.UserEntity;
import ru.msa.homework3.model.dto.request.CreateUserRequestDto;
import ru.msa.homework3.model.dto.request.UpdateUserRequestDto;
import ru.msa.homework3.model.dto.response.UserResponse;
import ru.msa.homework3.repository.UserRepository;
import ru.msa.homework3.service.UserService;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
import static ru.msa.homework3.exception.Homework3Error.*;

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

    Optional<UserEntity> userEntityOptional = userRepository.findById(id);
    if (userEntityOptional.isPresent()) {
      UserEntity userEntity = userEntityOptional.get();
      UserResponse userResponse = userMapper.toUserResponse(userEntity);
      log.info("Successfully received User: {} by id: {}", userResponse, id);

      return userResponse;
    } else {
      throw new Homework3Exception(USER_NOT_FOUND, NOT_FOUND);
    }
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

    log.info("Successfully deleted UserEntity with id: {}", id);
  }

  @Override
  @Transactional
  public void updateUser(Long id, UpdateUserRequestDto updateUserRequestDto) {
    log.info("Trying to update user by id: {} and request: {}", id, updateUserRequestDto);

    UserEntity userEntity = userMapper.toUserEntity(id, updateUserRequestDto);
    userRepository.save(userEntity);

    log.info("Successfully updated UserEntity with id: {}", id);
  }
}
