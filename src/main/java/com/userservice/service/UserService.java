package com.userservice.service;

import com.userservice.model.User;
import com.userservice.service.dto.request.RequestUserDto;
import com.userservice.service.dto.response.ResponseUserDto;
import java.util.UUID;

public interface UserService {

    ResponseUserDto getUserByLoginAndPassword(RequestUserDto userDto);

    ResponseUserDto saveUser(User user);

    String findAddressByUserId(String id);

    UUID findCourier();
}
