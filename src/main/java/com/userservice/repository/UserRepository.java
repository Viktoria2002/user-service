package com.userservice.repository;

import com.userservice.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository<T extends User> extends JpaRepository<T, UUID> {
  Optional<T> findByLoginAndPassword(String login, String password);

  boolean existsByLogin(String login);
}
