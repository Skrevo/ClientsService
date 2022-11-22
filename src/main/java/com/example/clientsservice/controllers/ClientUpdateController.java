package com.example.clientsservice.controllers;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

import static com.example.clientsservice.models.Client.*;

@Controller
public class ClientUpdateController {
    @Autowired
    private ClientsService clientsService;
    @GetMapping("clientUpdate")
    public String load(@RequestParam("clientId") Integer id, Model model) {
        Client client = clientsService.findById(id);
        model.addAttribute("client", client);
        /*
        for (Gender value : Gender.values()) {
            model.addAttribute(value.name(), value == client.getGender() ? "selected" : "");
        }

         */
        ModelMap genders = new ModelMap();
        for (Gender value : Gender.values()) {
            genders.put(value.name(), value == client.getGender() ? "selected" : "");
        }
        model.addAttribute("genders", genders.entrySet());
        return "clientUpdate";
    }
}
