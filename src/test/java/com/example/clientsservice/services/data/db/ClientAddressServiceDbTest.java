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

import javax.transaction.Transactional;

import static com.example.clientsservice.models.Client.Gender.FEMALE;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientAddressServiceDbTest {

    @Autowired
    private ClientsService clientsService;

    @Autowired
    private AddressService addressService;

    static Client a = new Client(3, "Testov", "Test", "Testovich", FEMALE,"test@test.com",
            null,null,null);

    static Address address1 = new Address(1L,"Kharkivska","Kharkiv","Kharkiv","Volonterska","55","12",null);
    static Address address2 = new Address(2L,"Kyjivska","Bilocerkovsky","Bila Cerkov","Levandovskogo","15","3",null);

    @Test
    @Order(1)
    void save() {
        a = clientsService.save(a);
        address1.setClient(a);
        address2.setClient(a);
        address1 = addressService.save(address1);
        address2 = addressService.save(address2);
    }

}
