package com.example.clientsservice.services.data.json;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.ClientsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceJson implements ClientsService {

    private final String clientsFile = "clients.json";

    @Override
    public List<Client> findAll() {
        try {
            List<Client> list = new Gson().fromJson(new FileReader(clientsFile), new TypeToken<>(){});
            if (list != null)
                return list;
        } catch (Exception ignored) {

        }
        return new ArrayList<>();
    }

    @Override
    public List<Client> findAllBySurnameAndNameAndPatronymic(String surname, String name, String patronymic) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Client findById(Integer id) {
        return null;
    }

    @Override
    public List<Client> saveAll(List<Client> clients) {
        try {
            FileWriter writer = new FileWriter(clientsFile);
            new Gson().toJson(clients, writer);
            writer.flush();
        }
        catch (Exception ignored) {
        }
        return clients;
    }

    @Override
    public Client save(Client client) {
        List<Client> list = findAll();
        list.add(client);
        saveAll(list);
        return client;
    }
}