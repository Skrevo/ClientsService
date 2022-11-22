package com.example.clientsservice.controllers;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class ClientsController {
    @Autowired
    private ClientsService clientsService;
    /*
    @GetMapping("/")
    public String load() {
        return "redirect:clients";
    }

     */
    @GetMapping("/clients")
    public String loadClients(Model model) {
        List<Client> list = clientsService.findAll();
        model.addAttribute("clients", list);
        return "clients";
    }
    @PostMapping("clientAddForm")
    public String clientAddForm(
            @RequestParam("surname") String surname,
            @RequestParam("name") String name,
            @RequestParam("patronymic") String patronymic,
            @RequestParam("gender") Client.Gender gender,
            @RequestParam("email") String email
    ) {
        Client client = new Client(0,surname,name,patronymic,gender,email,null,null,null);
        clientsService.save(client);
        return "redirect:";
    }

    @PostMapping("thisClientForm")
    public ModelAndView thisClientForm(Model model, @RequestParam("id") Integer id) {
        return new ModelAndView("clientUpdate", new ModelMap("clientId", id));
    }
}
