package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.Address;
import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.AddressService;
import com.example.clientsservice.services.data.ClientsService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static com.example.clientsservice.models.Client.Gender.FEMALE;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientAddressServiceDbTest {

    @Autowired
    private ClientsService clientsService;

    @Autowired
    private AddressService addressService;

    static Client a = new Client(0, "Testov", "Test", "Testovich", FEMALE,"test@test.com",
            null,null,null);

    static Address address1 = new Address(1L,"Kharkivska","Kharkiv","Kharkiv","Volonterska","55","12",null);
    @Test
    @Order(1)
    void save() {
        a = clientsService.save(a);
        address1 = addressService.save(address1);
        address1.setClient(a);
    }

    @Test
    @Order(2)
    void findByClientId() {
        Client actual = clientsService.findById(a.getId());
        System.out.println(actual);
        System.out.println(actual.getAddress());
    }

}
