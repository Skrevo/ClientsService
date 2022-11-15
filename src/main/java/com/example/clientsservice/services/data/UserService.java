package com.example.clientsservice.services.data;

import com.example.clientsservice.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    List<User> saveAll(List<User> users);

    User save(User user);
}
