package com.userservice.controller;

import com.userservice.model.User;
import com.userservice.service.UserService;
import com.userservice.service.dto.request.RequestUserDto;
import com.userservice.service.dto.response.ResponseUserDto;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service/users")
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/authorization")
  public ResponseUserDto getCourier(@RequestBody RequestUserDto userDto) {
    return userService.getUserByLoginAndPassword(userDto);
  }

  @GetMapping("/findCourier")
  public ResponseEntity<Void> getCourierOfDelivery() {
    UUID courierId = userService.findCourier();
    HttpHeaders headers = createHeaders("userId", String.valueOf(courierId));
    return createResponseEntity(headers);
  }

  @GetMapping("/getUserAddress")
  public ResponseEntity<Void> getUserAddress(@RequestHeader String userId) {
    String address = userService.findAddressByUserId(userId);
    HttpHeaders headers = createHeaders("address", address);
    return createResponseEntity(headers);
  }

  @PostMapping("/register")
  public ResponseUserDto saveClient(@RequestBody User user) {
    return userService.saveUser(user);
  }

  private HttpHeaders createHeaders(String headerName, String headerValue) {
    HttpHeaders headers = new HttpHeaders();
    headers.add(headerName, headerValue);
    return headers;
  }

  private ResponseEntity<Void> createResponseEntity(HttpHeaders headers) {
    return ResponseEntity.ok().headers(headers).body(null);
  }
}
