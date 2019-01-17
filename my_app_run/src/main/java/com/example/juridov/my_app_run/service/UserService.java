package com.example.juridov.my_app_run.service;

import com.example.juridov.my_app_run.entity.User;
import com.example.juridov.my_app_run.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public void addUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null){
            return;
        }
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), AuthorityUtils.createAuthorityList("ADMIN"));
    }
}
