package com.leticoin.webapp.controller;

import com.leticoin.webapp.dao.UserDAO;
import com.leticoin.webapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/users")
    public String index(Model model){
        model.addAttribute("users", userDAO.getUsers());
        return "list"; // list of users with parameters
    }

    @GetMapping("/users/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userDAO.getUserById(id));
        return "user"; // page of user profile
    }

    @GetMapping("/users/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "new"; // page with creation new user
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user){
        userDAO.create(user);
        return "redirect:/users"; // page that update list of users
    }
}
