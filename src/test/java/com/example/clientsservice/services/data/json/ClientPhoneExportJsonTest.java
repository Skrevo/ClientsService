package com.example.clientsservice.services.data.json;

import com.example.clientsservice.models.*;
import com.example.clientsservice.services.data.ClientsService;
import com.example.clientsservice.services.data.PhoneService;
import com.example.clientsservice.services.data.qualifiers.PhoneServiceQualifier;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static com.example.clientsservice.models.Client.Gender.FEMALE;
import static com.example.clientsservice.models.Client.Gender.MALE;

@Order(101)
@SpringBootTest
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientPhoneExportJsonTest {

    @Qualifier("clientServiceJson")
    @Autowired
    private ClientsService clientsService;

    static Phone phone1 = new Phone(0,"0999999999",null);
    static Phone phone2 = new Phone(0,"0955555555",null);
    static Client c1 = new Client(0, "T", "Te", "Tes", FEMALE,"test@test.com",
            null, null,null);

    static Client c2 = new Client(0, "V", "Ve", "Vek", MALE,"vekt@test.com",
            null, null,null);

    @Test
    void export() {

        List<Client> clientList = clientsService.saveAll(List.of(c1,c2));

        clientsService.saveAll(clientList);
    }
}
