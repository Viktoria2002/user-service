package com.userservice.model.enums;

import java.util.Random;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VehicleType {

  CAR("Car"),

  BICYCLE("Bicycle"),

  MOTORCYCLE("Motorcycle");

  private final String displayName;

  private static final Random RANDOM = new Random();

  public static VehicleType getRandom() {
    VehicleType[] values = VehicleType.values();
    int randomIndex = RANDOM.nextInt(values.length);
    return values[randomIndex];
  }
}