package com.userservice.service;

import com.userservice.model.Courier;
import com.userservice.service.dto.response.ResponseUserDto;
import java.util.UUID;

public interface CourierService extends BaseService<ResponseUserDto, Courier> {
  UUID findCourier();
}
