package ru.msa.homework3.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.msa.homework3.model.dao.UserEntity;
import ru.msa.homework3.model.dto.request.CreateUserRequestDto;
import ru.msa.homework3.model.dto.request.UpdateUserRequestDto;
import ru.msa.homework3.model.dto.response.UserResponse;

@Mapper
public interface UserMapper {

  UserResponse toUserResponse(UserEntity userEntity);

  @Mapping(target = "id", ignore = true)
  UserEntity toUserEntity(CreateUserRequestDto createUserRequestDto);

  UserEntity toUserEntity(Long id, UpdateUserRequestDto updateUserRequestDto);
}
