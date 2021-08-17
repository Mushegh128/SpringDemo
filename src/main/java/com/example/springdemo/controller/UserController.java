package com.example.springdemo.controller;
import com.example.springdemo.model.User;
import com.example.springdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public String users(ModelMap modelMap) {
        List<User> users = userRepository.findAll();
        modelMap.addAttribute("users",users);
        return "users";
    }

    @GetMapping("/addUser")
    public String addUser(ModelMap modelMap) {
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUserPost(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:user";
    }



}
