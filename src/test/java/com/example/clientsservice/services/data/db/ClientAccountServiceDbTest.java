package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.Account;
import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.AccountService;
import com.example.clientsservice.services.data.ClientsService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.example.clientsservice.models.Client.Gender.FEMALE;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientAccountServiceDbTest {
    @Autowired
    private ClientsService clientsService;

    @Autowired
    private AccountService accountService;

    static Client c1 = new Client(0, "a", "a", "a", FEMALE,"a@test.com",
            null,null,null);
    static Account account1 = new Account(1L,100,null);
    static Account account2 = new Account(2L,200,null);
    Set<Account> a = Set.of(account1,account2);

    @Test
    @Order(1)
    void save() {
        account1 = accountService.save(account1);
        account2 = accountService.save(account2);
        c1.setAccounts(a);
        c1 = clientsService.save(c1);
    }

    @Test
    @Order(2)
    @Transactional
    public void findByClientId() {
        Client actual = clientsService.findById(c1.getId());
        System.out.println(actual);
        System.out.println(actual.getAccounts());
    }
}
