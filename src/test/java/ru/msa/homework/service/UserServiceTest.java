package ru.msa.homework.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.msa.homework.exception.MsaHomeworkError;
import ru.msa.homework.exception.MsaHomeworkException;
import ru.msa.homework.mapper.UserMapper;
import ru.msa.homework.mapper.UserMapperImpl;
import ru.msa.homework.model.dao.UserEntity;
import ru.msa.homework.model.dto.request.CreateUserRequestDto;
import ru.msa.homework.model.dto.request.UpdateUserRequestDto;
import ru.msa.homework.repository.UserRepository;
import ru.msa.homework.service.impl.UserServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserServiceImpl.class, UserMapperImpl.class})
public class UserServiceTest {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @MockBean
    private UserRepository userRepository;

    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    public void testGetUserSuccess() {
        UserEntity userEntity = easyRandom.nextObject(UserEntity.class);
        userEntity.setId(1L);
        when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));

        var userResponse = userService.getUser(1L);
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getId()).isEqualTo(1L);
    }

    @Test
    public void testGetUserNotFound() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUser(1L))
            .isInstanceOf(MsaHomeworkException.class)
            .hasMessage(MsaHomeworkError.USER_NOT_FOUND.name());
    }

    @Test
    public void testCreateUserSuccess() {
        CreateUserRequestDto createUserRequestDto = easyRandom.nextObject(CreateUserRequestDto.class);
        UserEntity userEntity = userMapper.toUserEntity(createUserRequestDto);
        userEntity.setId(1L);

        when(userRepository.save(any())).thenReturn(userEntity);

        var userId = userService.createUser(createUserRequestDto);
        assertThat(userId).isNotNull();
        assertThat(userId).isEqualTo(1L);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void testDeleteUserSuccess() {
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(any());
    }

    @Test
    public void testUpdateUserSuccess() {
        UpdateUserRequestDto updateUserRequestDto = easyRandom.nextObject(UpdateUserRequestDto.class);
        UserEntity userEntity = userMapper.toUserEntity(1L, updateUserRequestDto);
        userEntity.setId(1L);

        when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        when(userRepository.save(any())).thenReturn(userEntity);

        userService.updateUser(1L, updateUserRequestDto);
        verify(userRepository, times(1)).save(any());
    }
}