package com.userservice.model;

import com.userservice.model.enums.VehicleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "couriers")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Courier extends User {

  @Column(name = "vehicle_type", nullable = false)
  private VehicleType vehicleType;

  @Builder
  public Courier(UUID id, String lastName, String firstName, String login,
      String password) {
    super(id, lastName, firstName, login, password);
    this.vehicleType = VehicleType.getRandom();
  }
}
