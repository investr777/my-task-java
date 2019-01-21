package com.example.juridov.my_app_run.controller;

import com.example.juridov.my_app_run.entity.Record;
import com.example.juridov.my_app_run.repository.RecordRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecordControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private RecordRepository recordRepository;

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void getAllRecordsOfUserWithListRecords() throws JsonProcessingException, JSONException {
        ResponseEntity<String> result = template.withBasicAuth("user1", "123")
                .getForEntity("/records", String.class);
        List<Record> expected = recordRepository.findAllByUserId(1L);
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        JSONAssert.assertEquals(mapper.writeValueAsString(expected), result.getBody(), false);
    }


    @Test
    public void addRecord() {
        Record record = new Record();
        record.setId(6L);
        record.setDate(System.currentTimeMillis());
        record.setTime(7);
        record.setDistance(999);
        record.setUserId(1L);
        HttpEntity<Record> entity = new HttpEntity(record, headers);
        ResponseEntity<String> result = template.withBasicAuth("user1", "123")
                .exchange("/records", HttpMethod.POST, entity, String.class);
        Record recordFromDb = recordRepository.findRecordById(6L);
        assertEquals(recordFromDb.getId(), record.getId());
        assertEquals(recordFromDb.getTime(), record.getTime());
        assertEquals(recordFromDb.getDistance(), record.getDistance());
        assertEquals(recordFromDb.getDate(), record.getDate());
        assertEquals(recordFromDb.getUserId(), record.getUserId());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void updateRecord() {
        Record record = new Record();
        record.setDate(1516300800000L);
        HttpEntity<Record> entity = new HttpEntity(record);
        ResponseEntity<Record> result = template.withBasicAuth("user1", "123")
                .exchange("/records/{recordId}", HttpMethod.PUT, entity, Record.class, 1);
        Record recordFromDb = recordRepository.findRecordById(1L);
        assertEquals(recordFromDb.getDate(), record.getDate());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void deleteRecord() {
        template.withBasicAuth("user1", "123")
                .delete("/records/{recordId}", 1);
        assertEquals(recordRepository.findRecordById(1L), null);
    }

}