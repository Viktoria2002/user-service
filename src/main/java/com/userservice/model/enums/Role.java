package com.userservice.model.enums;

import java.util.Random;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

  CLIENT("Client"),

  COURIER("Courier");

  private final String displayName;
}