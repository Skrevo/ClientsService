package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.Account;
import com.example.clientsservice.models.Client;
import com.example.clientsservice.models.Phone;
import com.example.clientsservice.services.data.AccountService;
import com.example.clientsservice.services.data.ClientsService;
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
public class AccountClientServiceDbTest {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientsService clientsService;

    static Client c1 = new Client(1, "a", "a", "a", FEMALE,"a@test.com",
            null,null,null);
    static Client c2 = new Client(2, "b", "b", "b", FEMALE,"b@test.com",
            null,null,null);

    static Account account1 = new Account(0L,100,null);
    Set<Client> a = Set.of(c1,c2);

    @Test
    @Order(1)
    void save() {
        c1 = clientsService.save(c1);
        c2 = clientsService.save(c2);
        account1.setClients(a);
        account1 = accountService.save(account1);
    }

    @Test
    @Order(2)
    @Transactional
    public void findByClientId() {
        Account actual = accountService.findById(account1.getId());
        System.out.println(actual);
        System.out.println(actual.getClients());
    }
}
