package com.example.clientsservice.services.data.json;

import com.example.clientsservice.models.Phone;
import com.example.clientsservice.models.User;
import com.example.clientsservice.services.data.PhoneService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class PhoneServiceJson implements PhoneService {

    private final String phonesFile = "phones.json";
    @Override
    public Phone save(Phone phone) {
        List<Phone> list = findAll();
        list.add(phone);
        saveAll(list);
        return phone;
    }

    private List<Phone> findAll() {
        try {
            List<Phone> list = new Gson().fromJson(new FileReader(phonesFile), new TypeToken<>(){});
            if (list != null)
                return list;
        } catch (Exception ignored) {

        }
        return new ArrayList<>();
    }

    @Override
    public List<Phone> saveAll(List<Phone> phones) {
        try {
            FileWriter writer = new FileWriter(phonesFile);
            new Gson().toJson(phones, writer);
            writer.flush();
        }
        catch (Exception ignored) {
        }
        return phones;
    }
}
