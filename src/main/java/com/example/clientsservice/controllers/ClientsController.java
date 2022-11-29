package com.example.clientsservice.controllers;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClientsController {
    @Autowired
    public ClientsService clientsService;
    @GetMapping("clients")
    public String load(Model model) {
        List<Client> list = clientsService.findAll();
        model.addAttribute("clients",list);
        return "clients";
    }
}
