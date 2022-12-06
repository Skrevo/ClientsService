package com.example.clientsservice;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.ClientsService;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@SpringBootTest
public class DateCalcTest {
    @Autowired
    private ClientsService clientsService;

    @Test
    void test() {
        LocalDate birth = LocalDate.of(1990, 12, 23);
        LocalDate now = LocalDate.now();
        LocalDate years = now.minus(birth.getYear(), ChronoUnit.YEARS);
        System.out.println(years.getYear());
        Period period = Period.between(birth, now);// более точный способ
        System.out.println(period.getYears());
        System.out.println(birth.compareTo(now));
    }

    @Test
    void clientSave() {
        Client client = new Client(0, "a", "a", "a", Client.Gender.MALE, "a@tgf", LocalDate.now(), null, null, null);
        client = clientsService.save(client);
        System.err.println(client);
    }

    @Test
    void clientDateJson() {
        Client client = new Client(0, "a", "a", "a", Client.Gender.MALE, "a@tgf", LocalDate.now(), null, null, null);
        /*
        Gson gson = new Gson();
        String json = gson.toJson(client);
        System.out.println(json);
        client = gson.fromJson(json, Client.class);
        System.out.println(client);
*/
        TypeAdapter<Client> typeAdapter = new TypeAdapter<>() {
            @Override
            public void write(JsonWriter jw, Client cl) throws IOException {
                jw.beginObject();
                jw.name("id").value(cl.getId());
                jw.name("surname").value(cl.getSurname());
                jw.name("name").value(cl.getName());
                jw.name("patronymic").value(cl.getPatronymic());
                jw.name("email").value(cl.getEmail());
                jw.name("gender").value(cl.getGender().name());
                jw.name("birthDate").value(cl.getBirthDate().toString());
                jw.endObject();
            }

            @Override
            public Client read(JsonReader jr) throws IOException {
                Client cl = new Client();
                jr.beginObject();
                jr.nextName();
                cl.setId(jr.nextInt());
                jr.nextName();
                cl.setSurname(jr.nextString());
                jr.nextName();
                cl.setName(jr.nextString());
                jr.nextName();
                cl.setPatronymic(jr.nextString());
                jr.nextName();
                cl.setEmail(jr.nextString());
                jr.nextName();
                cl.setGender(Client.Gender.valueOf(jr.nextString()));
                jr.nextName();
                cl.setBirthDate(LocalDate.parse(jr.nextString()));
                return cl;
            }
        };
        String json = typeAdapter.toJson(client);
        System.out.println(json);
        try {
            client = typeAdapter.fromJson(json);
            System.out.println(client);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}