package com.userservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Client extends User {

  @Column(name = "address", nullable = false)
  private String address;

  @Builder
  public Client(UUID id, String lastName, String firstName, String login, String password, String address) {
    super(id, lastName, firstName, login, password);
    this.address = address;
  }
}
