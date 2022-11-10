package com.example.clientsservice.repositories;

import com.example.clientsservice.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
