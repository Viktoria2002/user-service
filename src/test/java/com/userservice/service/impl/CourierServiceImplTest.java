package com.userservice.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.userservice.exception.UserNotFoundException;
import com.userservice.model.Client;
import com.userservice.model.Courier;
import com.userservice.repository.CourierRepository;
import com.userservice.service.dto.request.RequestUserDto;
import com.userservice.service.dto.response.ResponseUserDto;
import com.userservice.service.mapper.UserMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CourierServiceImplTest {

  @InjectMocks
  private CourierServiceImpl courierService;

  @Mock
  private CourierRepository courierRepository;

  @Mock
  private UserMapper userMapper;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    courierService = new CourierServiceImpl(courierRepository, userMapper);
  }

  @Test
  void testGetUserByLoginAndPassword() {
    RequestUserDto userDto = RequestUserDto.builder()
        .login("testlogin")
        .password("testpassword")
        .build();
    Courier courier = new Courier();
    ResponseUserDto expectedResponse = new ResponseUserDto();

    when(courierRepository.findByLoginAndPassword(userDto.getLogin(), userDto.getPassword()))
        .thenReturn(Optional.of(courier));
    when(userMapper.toDto(courier)).thenReturn(expectedResponse);

    ResponseUserDto actualResponse = courierService.getUserByLoginAndPassword(userDto);

    assertEquals(expectedResponse, actualResponse);
    verify(courierRepository).findByLoginAndPassword(userDto.getLogin(), userDto.getPassword());
    verify(userMapper).toDto(courier);
  }

  @Test
  void testGetUserByLoginAndPassword_UserNotFoundException() {
    RequestUserDto userDto = RequestUserDto.builder()
        .login("testlogin")
        .password("testpassword")
        .build();

    when(courierRepository.findByLoginAndPassword(userDto.getLogin(), userDto.getPassword()))
        .thenReturn(Optional.empty());

    assertThrows(UserNotFoundException.class,
        () -> courierService.getUserByLoginAndPassword(userDto));

    verify(courierRepository).findByLoginAndPassword(userDto.getLogin(), userDto.getPassword());
    verifyNoInteractions(userMapper);
  }

  @Test
  void testSaveUser() {
    Courier courier = new Courier();
    ResponseUserDto expectedResponse = new ResponseUserDto();

    when(courierRepository.save(courier)).thenReturn(courier);
    when(userMapper.toDto(courier)).thenReturn(expectedResponse);

    ResponseUserDto actualResponse = courierService.saveUser(courier);

    assertEquals(expectedResponse, actualResponse);
    verify(courierRepository).save(courier);
    verify(userMapper).toDto(courier);
  }

  @Test
  void testFindCourier() {
    List<Courier> courierList = new ArrayList<>();
    Courier courier1 = new Courier();
    courier1.setId(UUID.randomUUID());
    courierList.add(courier1);
    Courier courier2 = new Courier();
    courier2.setId(UUID.randomUUID());
    courierList.add(courier2);

    when(courierRepository.findAll()).thenReturn(courierList);

    UUID result = courierService.findCourier();

    assertTrue(result.equals(courier1.getId()) || result.equals(courier2.getId()));
  }
}
