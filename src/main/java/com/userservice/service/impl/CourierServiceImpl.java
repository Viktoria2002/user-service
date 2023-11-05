package com.userservice.service.impl;

import com.userservice.exception.DuplicateUserException;
import com.userservice.exception.UserNotFoundException;
import com.userservice.model.Courier;
import com.userservice.repository.CourierRepository;
import com.userservice.service.CourierService;
import com.userservice.service.dto.request.RequestUserDto;
import com.userservice.service.dto.response.ResponseUserDto;
import com.userservice.service.mapper.UserMapper;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourierServiceImpl implements CourierService {

  private final CourierRepository courierRepository;

  private final UserMapper userMapper;

  @Override
  public ResponseUserDto getUserByLoginAndPassword(RequestUserDto userDto) {
    Courier courier = courierRepository.findByLoginAndPassword(userDto.getLogin(),
        userDto.getPassword()).orElseThrow(
        () -> new UserNotFoundException("Courier was not fount"));
    return userMapper.toDto(courier);
  }

  @Override
  public ResponseUserDto saveUser(Courier courier) {
    if (courierRepository.existsByLogin(courier.getLogin())) {
      throw new DuplicateUserException("Courier is already registered");
    }
    Courier newCourier = courierRepository.save(courier);
    return userMapper.toDto(newCourier);
  }

  @Override
  public UUID findCourier() {
    List<Courier> courierList = courierRepository.findAll();
    int randomIndex = new Random().nextInt(courierList.size());
    return courierList.get(randomIndex).getId();
  }
}
