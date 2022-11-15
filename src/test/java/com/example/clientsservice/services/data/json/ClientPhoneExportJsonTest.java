package com.example.clientsservice.services.data.json;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@Order(101)
@SpringBootTest
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientPhoneExportJsonTest {
    @Test
    void export() {

    }
}
