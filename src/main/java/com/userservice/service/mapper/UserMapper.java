package com.userservice.service.mapper;

import com.userservice.model.Client;
import com.userservice.model.Courier;
import com.userservice.service.dto.response.ResponseUserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  ResponseUserDto toDto(Client client);

  ResponseUserDto toDto(Courier courier);
}
