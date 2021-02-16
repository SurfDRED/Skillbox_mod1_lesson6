package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.UserService;
import org.example.web.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final Logger logger = Logger.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String users(Model model) {
        logger.info("GET /users returns user_page.html");
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.getAllUsers());
        return "user_page";
    }

    @PostMapping("/register")
    public String saveUser(User user) {
        userService.saveUser(user);
        logger.info("current repository size: " + userService.getAllUsers().size());
        return "redirect:/users";
    }
}
