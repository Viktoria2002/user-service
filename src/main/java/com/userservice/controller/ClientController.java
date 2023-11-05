package com.userservice.controller;

import com.userservice.model.Client;
import com.userservice.service.ClientService;
import com.userservice.service.dto.request.RequestUserDto;
import com.userservice.service.dto.response.ResponseUserDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service/clients")
@AllArgsConstructor
public class ClientController {

  private final ClientService clientService;

  @GetMapping("/authorization")
  public ResponseUserDto getClient(@RequestBody RequestUserDto userDto) {
    return clientService.getUserByLoginAndPassword(userDto);
  }

  @PostMapping("/register")
  public ResponseUserDto saveClient(@RequestBody Client client) {
    return clientService.saveUser(client);
  }
}
