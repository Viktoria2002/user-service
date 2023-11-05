package com.userservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.userservice.model.User;
import com.userservice.service.UserService;
import com.userservice.service.dto.request.RequestUserDto;
import com.userservice.service.dto.response.ResponseUserDto;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
  @InjectMocks
  private UserController courierController;

  @Mock
  private UserService userService;

  @Test
  void testGetCourier() {
    RequestUserDto userDto = RequestUserDto.builder()
        .login("testlogin")
        .password("testpassword")
        .build();
    ResponseUserDto responseDto = ResponseUserDto.builder()
        .id(UUID.randomUUID())
        .build();
    when(userService.getUserByLoginAndPassword(userDto)).thenReturn(responseDto);

    ResponseUserDto response = courierController.getCourier(userDto);

    assertEquals(responseDto.getId(), response.getId());
  }

  @Test
  void testGetUserOfDelivery() {
    UUID userId = UUID.randomUUID();
    when(userService.findCourier()).thenReturn(userId);

    ResponseEntity response = courierController.getCourierOfDelivery();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(String.valueOf(userId), response.getHeaders().getFirst("userId"));
    assertEquals(null, response.getBody());
  }

  @Test
  void testGetUserAddress() {
    UUID userId = UUID.randomUUID();
    String address = "addr";
    when(userService.findAddressByUserId(any())).thenReturn(address);

    ResponseEntity response = courierController.getUserAddress(String.valueOf(userId));

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(address, response.getHeaders().getFirst("address"));
    assertEquals(null, response.getBody());
  }

  @Test
  void testSaveUser() {
    User user = new User();
    ResponseUserDto responseDto = ResponseUserDto.builder()
        .id(UUID.randomUUID())
        .build();
    when(userService.saveUser(user)).thenReturn(responseDto);

    ResponseUserDto response = courierController.saveClient(user);

    assertEquals(responseDto.getId(), response.getId());
  }
}