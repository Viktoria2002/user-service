package com.userservice.service.impl;

import com.userservice.exception.DuplicateUserException;
import com.userservice.exception.UserNotFoundException;
import com.userservice.model.Client;
import com.userservice.repository.ClientRepository;
import com.userservice.service.ClientService;
import com.userservice.service.dto.request.RequestUserDto;
import com.userservice.service.dto.response.ResponseUserDto;
import com.userservice.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;

  private final UserMapper userMapper;

  @Override
  public ResponseUserDto getUserByLoginAndPassword(RequestUserDto userDto) {
    Client client = clientRepository.findByLoginAndPassword(userDto.getLogin(),
        userDto.getPassword()).orElseThrow(
        () -> new UserNotFoundException("Client was not fount"));
    return userMapper.toDto(client);
  }

  @Override
  public ResponseUserDto saveUser(Client client) {
    if (clientRepository.existsByLogin(client.getLogin())) {
      throw new DuplicateUserException("Client is already registered");
    }
    Client newClient = clientRepository.save(client);
    return userMapper.toDto(newClient);
  }
}
