package com.example.clientsservice.controllers;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ClientsController {
    @Autowired
    public ClientsService clientsService;
    @GetMapping("clients")
    public String load(Model model) {
        List<Client> list = clientsService.findAll();
        model.addAttribute("clients",list);
        model.addAttribute("genders", Client.Gender.values());
        return "clients";
    }
    @PostMapping("addClientForm")
    public String addClientForm(@ModelAttribute Client client) {
        clientsService.save(client);
        return "redirect:clients";
    }

    @PostMapping
    public ModelAndView openClientForm(Integer id) {
        return new ModelAndView("redirect:clientUpdate", new ModelMap("id", id));
    }
}
