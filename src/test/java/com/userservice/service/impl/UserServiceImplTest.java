package com.userservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.userservice.exception.UserNotFoundException;
import com.userservice.model.User;
import com.userservice.repository.UserRepository;
import com.userservice.service.dto.request.RequestUserDto;
import com.userservice.service.dto.response.ResponseUserDto;
import com.userservice.service.mapper.UserMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @InjectMocks
  private UserServiceImpl courierService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private UserMapper userMapper;

  @Test
  void testGetUserByLoginAndPassword() {
    RequestUserDto userDto = RequestUserDto.builder()
        .login("testlogin")
        .password("testpassword")
        .build();
    User courier = new User();
    ResponseUserDto expectedResponse = new ResponseUserDto();

    when(userRepository.findByLoginAndPassword(userDto.getLogin(), userDto.getPassword()))
        .thenReturn(Optional.of(courier));
    when(userMapper.toDto(courier)).thenReturn(expectedResponse);

    ResponseUserDto actualResponse = courierService.getUserByLoginAndPassword(userDto);

    assertEquals(expectedResponse, actualResponse);
    verify(userRepository).findByLoginAndPassword(userDto.getLogin(), userDto.getPassword());
    verify(userMapper).toDto(courier);
  }

  @Test
  void testGetUserByLoginAndPassword_UserNotFoundException() {
    RequestUserDto userDto = RequestUserDto.builder()
        .login("testlogin")
        .password("testpassword")
        .build();

    when(userRepository.findByLoginAndPassword(userDto.getLogin(), userDto.getPassword()))
        .thenReturn(Optional.empty());

    assertThrows(UserNotFoundException.class,
        () -> courierService.getUserByLoginAndPassword(userDto));

    verify(userRepository).findByLoginAndPassword(userDto.getLogin(), userDto.getPassword());
    verifyNoInteractions(userMapper);
  }

  @Test
  void testSaveUser() {
    User user = new User();
    ResponseUserDto expectedResponse = new ResponseUserDto();

    when(userRepository.save(user)).thenReturn(user);
    when(userMapper.toDto(user)).thenReturn(expectedResponse);

    ResponseUserDto actualResponse = courierService.saveUser(user);

    assertEquals(expectedResponse, actualResponse);
    verify(userRepository).save(user);
    verify(userMapper).toDto(user);
  }

  @Test
  void testFindCourier() {
    List<User> userList = new ArrayList<>();
    User courier1 = new User();
    courier1.setId(UUID.randomUUID());
    userList.add(courier1);
    User courier2 = new User();
    courier2.setId(UUID.randomUUID());
    userList.add(courier2);
    when(userRepository.findUsersByRole(any())).thenReturn(userList);

    UUID result = courierService.findCourier();

    assertTrue(result.equals(courier1.getId()) || result.equals(courier2.getId()));
  }

  @Test
  void testFindCourier_UserNotFoundException() {
    List<User> userList = new ArrayList<>();
    when(userRepository.findUsersByRole(any())).thenReturn(userList);

    assertThrows(UserNotFoundException.class,
        () -> courierService.findCourier());
  }
}
