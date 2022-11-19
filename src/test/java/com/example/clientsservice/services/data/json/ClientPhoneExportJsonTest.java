package com.example.clientsservice.services.data.json;

import com.example.clientsservice.models.*;
import com.example.clientsservice.services.data.ClientsService;
import com.example.clientsservice.services.data.PhoneService;
import com.example.clientsservice.services.data.qualifiers.PhoneServiceQualifier;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.example.clientsservice.models.Client.Gender.FEMALE;
import static com.example.clientsservice.models.Client.Gender.MALE;

@Order(101)
@SpringBootTest
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientPhoneExportJsonTest {

    @Autowired
    private PhoneService phoneService;

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
    @Order(2)
    void export() {

        c1.setPhones(Collections.singleton(phone1));
        c2.setPhones(Collections.singleton(phone2));
        List<Client> clientList = clientsService.saveAll(List.of(c1,c2));

        clientsService.saveAll(clientList);
    }

    @Test
    @Order(1)
    void save(){

        c1 = clientsService.save(c1);
        c2 = clientsService.save(c2);
        phone1.setClient(c1);
        phone2.setClient(c2);
        phone1 = phoneService.save(phone1);
        phone2 = phoneService.save(phone2);

    }
}
