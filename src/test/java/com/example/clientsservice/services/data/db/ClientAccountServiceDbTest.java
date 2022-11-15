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
import java.util.HashSet;
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
            null,null,new HashSet<>());
    static Client c2 = new Client(0, "b", "b", "b", FEMALE,"b@test.com",
            null,null,new HashSet<>());
    static Account account1 = new Account(0L,100,new HashSet<>());
    static Account account2 = new Account(0L,200,new HashSet<>());

    @Test
    @Order(1)
    void save() {
        List<Client> clientList = clientsService.saveAll(List.of(c1,c2));
        List<Account> accountList = accountService.saveAll(List.of(account1,account2));
        clientList.forEach(client -> client.getAccounts().addAll(accountList));

        clientsService.saveAll(clientList);
        accountService.saveAll(accountList);
    }

    @Test
    @Order(2)
    @Transactional
    public void findByClientId() {
        Client actual = clientsService.save(c1);
        c1.setId(actual.getId());
        System.out.println(actual);
        System.out.println(actual.getAccounts());
    }
}
