package com.example.clientsservice.services.data;

import com.example.clientsservice.models.Client;

import java.util.Collection;
import java.util.List;

public interface ClientsService {
    Client save(Client client);

    List<Client> findAll();

    List<Client> findAllBySurnameAndNameAndPatronymic(String surname, String name, String patronymic);

    void deleteAll();

    Client findById(Integer id);
}
