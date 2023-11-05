package com.userservice.service;

import com.userservice.service.dto.request.RequestUserDto;

public interface BaseService<T, K> {

    T getUserByLoginAndPassword(RequestUserDto userDto);

    T saveUser(K user);
}
