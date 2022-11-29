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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientUpdateController {
    @Autowired
    private ClientsService clientsService;

    @GetMapping("clientUpdate")
    public String load(@RequestParam("id") Integer id, Model model) {
        Client client = clientsService.findById(id);
        model.addAttribute("client",client);
        model.addAttribute("genders", Client.Gender.values());
        return "clientUpdate";
    }

    @PostMapping("updateClientForm")
    public ModelAndView method(@ModelAttribute Client client) {
        clientsService.save(client);
        return new ModelAndView("redirect:clientUpdate", new ModelMap("id", client.getId()));
    }
}
