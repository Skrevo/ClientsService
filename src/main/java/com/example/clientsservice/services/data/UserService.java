package com.example.clientsservice.services.data;

import com.example.clientsservice.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User save(User user);

    User findById(Integer id);

    User findByUsername(String username);

    List<User> saveAll(List<User> users);
}
