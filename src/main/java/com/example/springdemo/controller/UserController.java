package com.example.springdemo.controller;

import com.example.springdemo.model.User;
import com.example.springdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Value("${upload.dir}")
    private String uploadDir;



    @GetMapping("/user")
    public String users(ModelMap modelMap) {
        List<User> users = userRepository.findAll();
        modelMap.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/addUser")
    public String addUser(ModelMap modelMap) {
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUserPost(@ModelAttribute User user, @RequestParam("picture") MultipartFile multipartFile) {
        if (!multipartFile.isEmpty() && multipartFile != null) {
            String picUrl = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            try {
                multipartFile.transferTo(new File(uploadDir + File.separator + picUrl));
            } catch (IOException e) {
                e.printStackTrace();
            }
            user.setPicture_url(picUrl);
        }
        userRepository.save(user);
        return "redirect:/user";
    }


}
