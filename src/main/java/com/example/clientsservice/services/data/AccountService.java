package com.example.clientsservice.services.data;

import com.example.clientsservice.models.Account;

import java.util.List;

public interface AccountService {
    Account save(Account account);

    Account findById(Long id);

    List<Account> saveAll(List<Account> accounts);

    List<Account> findAllByAmountEquals(int amount);
}
