package com.example.juridov.my_app_run.controller;

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
public class ReportControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getRecordsOfWeeks() {
        ResponseEntity<String> result = template.withBasicAuth("user1", "123")
                .getForEntity("/report/by_weeks", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}