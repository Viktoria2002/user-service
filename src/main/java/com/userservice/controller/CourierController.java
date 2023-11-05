package com.userservice.controller;

import com.userservice.model.Courier;
import com.userservice.service.CourierService;
import com.userservice.service.dto.request.RequestUserDto;
import com.userservice.service.dto.response.ResponseUserDto;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service/couriers")
@AllArgsConstructor
public class CourierController {

  private final CourierService courierService;

  @GetMapping("/authorization")
  public ResponseUserDto getCourier(@RequestBody RequestUserDto userDto) {
    return courierService.getUserByLoginAndPassword(userDto);
  }

  @GetMapping("/findCourier")
  public ResponseEntity getCourierOfDelivery() {
    UUID courierId = courierService.findCourier();
    HttpHeaders headers = new HttpHeaders();
    headers.add("courierId", String.valueOf(courierId));
    return ResponseEntity.ok()
        .headers(headers)
        .body(null);
  }

  @PostMapping("/register")
  public ResponseUserDto saveClient(@RequestBody Courier courier) {
    return courierService.saveUser(courier);
  }
}
