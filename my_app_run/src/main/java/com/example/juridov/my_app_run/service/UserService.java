package com.example.juridov.my_app_run.service;

import com.example.juridov.my_app_run.entity.User;
import com.example.juridov.my_app_run.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }
}
