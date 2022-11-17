package com.example.clientsservice.services.data.json;

import com.example.clientsservice.models.Phone;
import com.example.clientsservice.services.data.ClientsService;
import com.example.clientsservice.services.data.PhoneService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@Order(101)
@SpringBootTest
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientPhoneExportJsonTest {

    @Qualifier("clientServiceJson")
    @Autowired
    private ClientsService clientsService;

    @Qualifier("phoneServiceJson")
    @Autowired
    private PhoneService phoneService;
    @Test
    void export() {

    }
}
