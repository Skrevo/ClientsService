package com.example.clientsservice;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.models.Client.Gender;
import com.example.clientsservice.models.User;
import com.example.clientsservice.repositories.ClientRepository;
import com.example.clientsservice.services.data.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static com.example.clientsservice.models.Client.Gender.*;

@SpringBootApplication
public class ClientsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientsServiceApplication.class, args);
    }
    private final ClientRepository clientRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ClientsServiceApplication(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady(){
        /*
        Client a = new Client(0, "a", "a", "a", MALE,"a@test.com",
                null,null,null);
        Client b = new Client(0, "b", "b", "b", MALE,"b@test.com",
                null,null,null);
        clientRepository.saveAll(List.of(a,b));
*/
        User admin = new User(0, "a","a",User.Role.ADMIN, User.Status.ACTIVE,"a@gmail.com");
        User user = new User(0, "u","u",User.Role.USER, User.Status.ACTIVE,"u@gmail.com");
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveAll(List.of(admin,user));
    }

}
