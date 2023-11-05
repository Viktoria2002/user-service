package com.userservice.service.impl;

import com.userservice.exception.DuplicateUserException;
import com.userservice.exception.UserNotFoundException;
import com.userservice.model.User;
import com.userservice.model.enums.Role;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;
import com.userservice.service.dto.request.RequestUserDto;
import com.userservice.service.dto.response.ResponseUserDto;
import com.userservice.service.mapper.UserMapper;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  @Override
  public ResponseUserDto getUserByLoginAndPassword(RequestUserDto userDto) {
    User user = userRepository.findByLoginAndPassword(userDto.getLogin(),
        userDto.getPassword()).orElseThrow(
        () -> new UserNotFoundException("User was not fount"));
    return userMapper.toDto(user);
  }

  @Override
  public ResponseUserDto saveUser(User user) {
    if (userRepository.existsByLogin(user.getLogin())) {
      throw new DuplicateUserException("User is already registered");
    }
    User newUser = userRepository.save(user);
    return userMapper.toDto(newUser);
  }

  @Override
  public UUID findCourier() {
    List<User> courierList = userRepository.findUsersByRole(Role.COURIER);
    int randomIndex = new Random().nextInt(courierList.size());
    return courierList.get(randomIndex).getId();
  }
}
