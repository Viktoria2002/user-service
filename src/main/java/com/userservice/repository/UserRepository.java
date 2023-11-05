package com.userservice.repository;

import com.userservice.model.User;
import com.userservice.model.enums.Role;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByLoginAndPassword(String login, String password);

  List<User> findUsersByRole(Role role);

  boolean existsByLogin(String login);
}
