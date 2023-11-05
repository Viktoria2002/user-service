package com.userservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.userservice.model.Client;
import com.userservice.service.ClientService;
import com.userservice.service.dto.request.RequestUserDto;
import com.userservice.service.dto.response.ResponseUserDto;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class ClientControllerTest {

  @InjectMocks
  private ClientController clientController;

  @Mock
  private ClientService clientService;


  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    clientController = new ClientController(clientService);
  }

  @Test
  void testGetClient() {
    RequestUserDto userDto = RequestUserDto.builder()
        .login("testlogin")
        .password("testpassword")
        .build();
    ResponseUserDto responseDto = ResponseUserDto.builder()
        .id(UUID.randomUUID())
        .build();
    when(clientService.getUserByLoginAndPassword(userDto)).thenReturn(responseDto);

    ResponseUserDto response = clientController.getClient(userDto);

    assertEquals(responseDto.getId(), response.getId());
  }

  @Test
  void testSaveClient() {
    Client client = new Client();
    ResponseUserDto responseDto = ResponseUserDto.builder()
        .id(UUID.randomUUID())
        .build();
    when(clientService.saveUser(client)).thenReturn(responseDto);

    ResponseUserDto response = clientController.saveClient(client);

    assertEquals(responseDto.getId(), response.getId());
  }
}
