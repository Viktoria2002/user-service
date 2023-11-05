//package com.userservice.controller;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//import com.userservice.service.dto.request.RequestUserDto;
//import com.userservice.service.dto.response.ResponseUserDto;
//import java.util.UUID;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//class CourierControllerTest {
//  @InjectMocks
//  private UserController courierController;
//
//  @Mock
//  private CourierService courierService;
//
//  @BeforeEach
//  void setup() {
//    MockitoAnnotations.openMocks(this);
//    courierController = new UserController(courierService);
//  }
//
//  @Test
//  void testGetCourier() {
//    RequestUserDto userDto = RequestUserDto.builder()
//        .login("testlogin")
//        .password("testpassword")
//        .build();
//    ResponseUserDto responseDto = ResponseUserDto.builder()
//        .id(UUID.randomUUID())
//        .build();
//    when(courierService.getUserByLoginAndPassword(userDto)).thenReturn(responseDto);
//
//    ResponseUserDto response = courierController.getCourier(userDto);
//
//    assertEquals(responseDto.getId(), response.getId());
//  }
//
//  @Test
//  void testGetCourierOfDelivery() {
//    UUID courierId = UUID.randomUUID();
//    when(courierService.findCourier()).thenReturn(courierId);
//
//    ResponseEntity response = courierController.getCourierOfDelivery();
//
//    assertEquals(HttpStatus.OK, response.getStatusCode());
//    assertEquals(String.valueOf(courierId), response.getHeaders().getFirst("courierId"));
//    assertEquals(null, response.getBody());
//  }
//
//  @Test
//  void testSaveCourier() {
//    Courier courier = new Courier();
//    ResponseUserDto responseDto = ResponseUserDto.builder()
//        .id(UUID.randomUUID())
//        .build();
//    when(courierService.saveUser(courier)).thenReturn(responseDto);
//
//    ResponseUserDto response = courierController.saveClient(courier);
//
//    assertEquals(responseDto.getId(), response.getId());
//  }
//}