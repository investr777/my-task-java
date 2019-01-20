package com.example.juridov.my_app_run.controller;

import com.example.juridov.my_app_run.entity.User;
import com.example.juridov.my_app_run.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void addUser() {
        User user = new User();
        user.setId(3L);
        user.setUsername("admin");
        user.setPassword("root");
        ResponseEntity<User> response = restTemplate.postForEntity("/registration", user, User.class);
        User userFromDB = userRepository.findById(3L).get();
        assertEquals(userFromDB.getId(), user.getId());
        assertEquals(userFromDB.getUsername(), user.getUsername());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}