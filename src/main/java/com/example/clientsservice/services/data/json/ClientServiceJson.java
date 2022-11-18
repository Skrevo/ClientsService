package com.example.clientsservice.services.data.json;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.ClientsService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.List;

@Service
public class ClientServiceJson implements ClientsService {

    private final String clientsFile = "clients.json";
    @Override
    public Client save(Client client) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return null;
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
}
