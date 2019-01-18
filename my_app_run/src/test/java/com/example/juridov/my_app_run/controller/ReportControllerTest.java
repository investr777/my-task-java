package com.example.juridov.my_app_run.controller;

import com.example.juridov.my_app_run.entity.report.Report;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getRecordsOfWeeksWithModel() throws JSONException, JsonProcessingException {
        ResponseEntity<String> result = template.withBasicAuth("user1", "123")
                .getForEntity("/report/by_weeks", String.class);
        Report report = new Report();
        report.setAvSpeed(282.05);
        report.setAvTime(8);
        report.setTotalDistance(4000);
        report.setWeekNumber(3);
        report.setWeekStart(1547424000000L);
        report.setWeekEnd(1547942400000L);
        List<Report> expected = new ArrayList<>();
        expected.add(report);
        ObjectMapper mapper = new ObjectMapper();
        JSONAssert.assertEquals(mapper.writeValueAsString(expected), result.getBody(), false);
    }

    @Test
    public void getRecordsOfWeeksWithHttpStatus() {
        ResponseEntity<String> result = template.withBasicAuth("user2", "321")
                .getForEntity("/report/by_weeks", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}