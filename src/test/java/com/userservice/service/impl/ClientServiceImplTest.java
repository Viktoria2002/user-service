package com.userservice.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.userservice.exception.UserNotFoundException;
import com.userservice.model.Client;
import com.userservice.repository.ClientRepository;
import com.userservice.service.dto.request.RequestUserDto;
import com.userservice.service.dto.response.ResponseUserDto;
import com.userservice.service.mapper.UserMapper;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ClientServiceImplTest {

  @InjectMocks
  private ClientServiceImpl clientService;

  @Mock
  private ClientRepository clientRepository;

  @Mock
  private UserMapper userMapper;


  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
    clientService = new ClientServiceImpl(clientRepository, userMapper);
  }

  @Test
  void testGetUserByLoginAndPassword() {
    RequestUserDto userDto = RequestUserDto.builder()
        .login("testlogin")
        .password("testpassword")
        .build();
    Client client = new Client();
    ResponseUserDto expectedResponse = new ResponseUserDto();

    when(clientRepository.findByLoginAndPassword(userDto.getLogin(), userDto.getPassword()))
        .thenReturn(Optional.of(client));
    when(userMapper.toDto(client)).thenReturn(expectedResponse);

    ResponseUserDto actualResponse = clientService.getUserByLoginAndPassword(userDto);

    assertEquals(expectedResponse, actualResponse);
    verify(clientRepository).findByLoginAndPassword(userDto.getLogin(), userDto.getPassword());
    verify(userMapper).toDto(client);
  }

  @Test
  void testGetUserByLoginAndPasswordWithUserNotFoundException() {
    RequestUserDto userDto = RequestUserDto.builder()
        .login("testlogin")
        .password("testpassword")
        .build();

    when(clientRepository.findByLoginAndPassword(userDto.getLogin(), userDto.getPassword()))
        .thenReturn(Optional.empty());

    assertThrows(UserNotFoundException.class,
        () -> clientService.getUserByLoginAndPassword(userDto));

    verify(clientRepository).findByLoginAndPassword(userDto.getLogin(), userDto.getPassword());
    verifyNoInteractions(userMapper);
  }

  @Test
  void testSaveUser() {
    Client client = new Client();
    ResponseUserDto expectedResponse = new ResponseUserDto();

    when(clientRepository.save(client)).thenReturn(client);
    when(userMapper.toDto(client)).thenReturn(expectedResponse);

    ResponseUserDto actualResponse = clientService.saveUser(client);

    assertEquals(expectedResponse, actualResponse);
    verify(clientRepository).save(client);
    verify(userMapper).toDto(client);
  }
}
