package com.userservice.service.mapper;

import com.userservice.model.User;
import com.userservice.service.dto.response.ResponseUserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  ResponseUserDto toDto(User user);
}
