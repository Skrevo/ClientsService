package com.example.clientsservice.ui.controllers;

import com.example.clientsservice.models.User;
import com.example.clientsservice.services.data.UserService;
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
public class UsersController {
    @Autowired
    public UserService userService;

    @GetMapping("users")
    public String load(Model model) {
        List<User> list = userService.findAll();
        model.addAttribute("users",list);
        model.addAttribute("roles", User.Role.values());
        model.addAttribute("statuses", User.Status.values());
        return "users";
    }
    @PostMapping("addUserForm")
    public String addClientForm(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:users";
    }

    @PostMapping("openUserForm")
    public ModelAndView openUserForm(Integer id) {
        return new ModelAndView("redirect:userUpdate", new ModelMap("id", id));
    }
}
