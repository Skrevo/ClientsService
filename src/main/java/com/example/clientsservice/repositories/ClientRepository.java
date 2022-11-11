package com.example.clientsservice.repositories;

import com.example.clientsservice.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findAllBySurnameAndNameAndPatronymic(String surname, String name, String patronymic);

}
