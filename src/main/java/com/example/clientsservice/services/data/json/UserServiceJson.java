package com.example.clientsservice.services.data.json;

import com.example.clientsservice.models.User;
import com.example.clientsservice.services.data.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceJson implements UserService {

    private final String usersFile = "users.json";

    @Override
    public List<User> findAll() {
        try {
           List<User> list = new Gson().fromJson(new FileReader(usersFile), new TypeToken<>(){});
           if (list != null)
               return list;
        } catch (Exception ignored) {

        }
        return new ArrayList<>();
    }

    @Override
    public List<User> saveAll(List<User> users) {
        try {
            FileWriter writer = new FileWriter(usersFile);
            new Gson().toJson(users, writer);
            writer.flush();
        }
        catch (Exception ignored) {
        }
        return users;
    }

    @Override
    public User save(User user) {
        List<User> list = findAll();
        list.add(user);
        saveAll(list);
        return user;
    }
}
