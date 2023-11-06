package com.userservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.userservice.model.User;
import com.userservice.model.enums.Role;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"file:src/test/resources/db/clearAll.sql","file:src/test/resources/db/inserts.sql"})
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void testFindUserByLoginAndPassword() {
    User user = User.builder()
        .id(UUID.fromString("9aa344c4-a549-49c1-a025-ccc7c9771d48"))
        .address("addr1")
        .login("login1")
        .password("password1")
        .role(Role.CLIENT)
        .build();

    User foundedUser = userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword()).get();

    assertEquals(user, foundedUser);
  }

  @Test
  void testFindUsersByRole() {
    int expectedSize = 2;

    List<User> clients = userRepository.findUsersByRole(Role.CLIENT);

    assertEquals(expectedSize, clients.size());
  }

  @Test
  void testExistsByLogin() {
    boolean result = userRepository.existsByLogin("login3");

    assertTrue(result);
  }
}
