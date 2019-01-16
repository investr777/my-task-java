package com.example.juridov.my_app_run.controller;

import com.example.juridov.my_app_run.entity.User;
import com.example.juridov.my_app_run.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/registration")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
}
