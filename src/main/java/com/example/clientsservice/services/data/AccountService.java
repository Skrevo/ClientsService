package com.example.clientsservice.services.data;

import com.example.clientsservice.models.Account;

public interface AccountService {
    Account save(Account account);

    Account findById(Long id);
}
