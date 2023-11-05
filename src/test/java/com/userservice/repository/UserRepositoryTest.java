//package com.userservice.repository;
//
//import java.util.UUID;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.jdbc.Sql;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Sql(scripts = {"file:src/test/resources/db/clearAll.sql","file:src/test/resources/db/inserts.sql"})
//class UserRepositoryTest {
//
//  @Autowired
//  private ClientRepository clientRepository;
//
//  @Autowired
//  private CourierRepository courierRepository;
//
//  @Test
//  void testFindClientByLoginAndPassword() {
//    Client client = Client.builder()
//        .id(UUID.fromString("675dda20-3b7e-4f28-b88d-8f4816c36810"))
//        .firstName("FN")
//        .lastName("LN")
//        .login("login")
//        .password("password")
//        .address("addr")
//        .build();
//
//    Client foundedClient = clientRepository.findByLoginAndPassword(client.getLogin(), client.getPassword()).get();
//
//    assertEquals(client, foundedClient);
//  }
//
//  @Test
//  void testFindCourierByLoginAndPassword() {
//    Courier courier = Courier.builder()
//        .id(UUID.fromString("675dda20-3b7e-4f28-b88d-8f4816c36810"))
//        .firstName("FN1")
//        .lastName("LN1")
//        .login("login1")
//        .password("pass1")
//        .build();
//
//    Courier foundedCourier = courierRepository.findByLoginAndPassword(courier.getLogin(), courier.getPassword()).get();
//
//    assertEquals(courier, foundedCourier);
//  }
//}
