package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.models.Phone;
import com.example.clientsservice.services.data.ClientsService;
import com.example.clientsservice.services.data.PhoneService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Set;

import static com.example.clientsservice.models.Client.Gender.FEMALE;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientPhoneServiceDbTest {
    @Autowired
    private ClientsService clientsService;
    @Autowired
    private PhoneService phoneService;
    static Client a = new Client(0, "a", "a", "a", FEMALE,"a@test.com",
            null,null,null);
    static Phone p1 = new Phone(0,"111",null);
    static Phone p2 = new Phone(0,"222", null);
    @Test
    @Order(1)
    void save() {
        a = clientsService.save(a);
        p1.setClient(a);
        p2.setClient(a);
        p1 = phoneService.save(p1);
        p2 = phoneService.save(p2);
    }

    @Test
    @Order(2)
    @Transactional
    public void findByClientId() {
        Client actual = clientsService.findById(a.getId());
        System.out.println(actual);
        System.out.println(actual.getPhones());
    }
}
