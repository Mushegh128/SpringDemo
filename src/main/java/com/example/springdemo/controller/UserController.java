package com.example.springdemo.controller;
import com.example.springdemo.model.User;
import com.example.springdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public String users() {
        List<User> all = userRepository.findAll();

        return "users";
    }

}
