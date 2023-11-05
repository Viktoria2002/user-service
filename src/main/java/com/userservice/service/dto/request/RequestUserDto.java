package com.userservice.service.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestUserDto {

  private String login;

  private String password;
}
