package com.gmail.goyter012.campus.controller;

import com.gmail.goyter012.campus.model.Role;
import com.gmail.goyter012.campus.model.User;
import com.gmail.goyter012.campus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegistrationController {


    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {

        User userFromDb = userService.findUserByUsername(user.getUsername());
        if (userFromDb != null) {
            model.addAttribute("message", "Error! User already exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        userService.saveUser(user);

        return "redirect:/login";
    }


}
