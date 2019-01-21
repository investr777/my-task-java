package com.example.juridov.my_app_run.controller;

import com.example.juridov.my_app_run.entity.report.Report;
import com.example.juridov.my_app_run.service.ReportService;
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

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private ReportService reportService;

    @Test
    public void getRecordsOfWeeksWithModel() throws JSONException, JsonProcessingException {
        ResponseEntity<String> result = template.withBasicAuth("user1", "123")
                .getForEntity("/report/by_weeks", String.class);
        List<Report> expected = reportService.getReportOfRecords(1L);
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        JSONAssert.assertEquals(mapper.writeValueAsString(expected), result.getBody(), false);
    }
}