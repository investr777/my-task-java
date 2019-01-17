package com.example.juridov.my_app_run.controller;

import com.example.juridov.my_app_run.entity.User;
import com.example.juridov.my_app_run.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/registration")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }
}
