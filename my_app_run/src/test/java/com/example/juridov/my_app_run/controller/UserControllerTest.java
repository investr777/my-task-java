package com.example.juridov.my_app_run.controller;

import com.example.juridov.my_app_run.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("root");
        ResponseEntity<User> response = restTemplate.postForEntity("/registration", user, User.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}