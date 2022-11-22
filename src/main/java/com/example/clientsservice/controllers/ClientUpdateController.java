package com.example.clientsservice.controllers;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientUpdateController {
    @Autowired
    private ClientsService clientsService;
    @GetMapping("clientUpdate")
    public String load(@RequestParam("clientId") Integer id, Model model) {
        Client client = clientsService.findById(id);
        model.addAttribute("client", client);
        return "clientUpdate";
    }
}
