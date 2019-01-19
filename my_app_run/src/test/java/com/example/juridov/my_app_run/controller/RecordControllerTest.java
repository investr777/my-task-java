package com.example.juridov.my_app_run.controller;

import com.example.juridov.my_app_run.entity.Record;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecordControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getAllRecordsOfUSerWithHttpStatus() {
        ResponseEntity<String> result = template.withBasicAuth("user1", "123")
                .getForEntity("/records", String.class);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getAllRecordsOfUSerWithListRecords() throws JsonProcessingException, JSONException {
        ResponseEntity<String> result = template.withBasicAuth("user1", "123")
                .getForEntity("/records", String.class);
        List<Record> expected = new ArrayList<>();
        Record record1 = new Record();
        record1.setUserId(1L);
        record1.setDate(System.currentTimeMillis());
        record1.setTime(3);
        record1.setDistance(1000);
        expected.add(record1);
        Record record2 = new Record();
        record2.setUserId(1L);
        record2.setDate(System.currentTimeMillis());
        record2.setTime(13);
        record2.setDistance(3000);
        expected.add(record2);
        ObjectMapper mapper = new ObjectMapper();
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        JSONAssert.assertEquals(mapper.writeValueAsString(expected), result.getBody(), false);
    }


    @Test
    public void addRecord() {
        Record record = new Record();
        record.setDate(System.currentTimeMillis());
        record.setTime(3);
        record.setDistance(1000);
        ResponseEntity<Record> result = template.withBasicAuth("user1", "123")
                .postForEntity("/records", record, Record.class);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void updateRecord() {
        Record record = new Record();
        record.setDate(1546300800000L);
        HttpEntity<Record> entity = new HttpEntity(record);
        ResponseEntity<Record> result = template.withBasicAuth("user1", "123")
                .exchange("/records/{recordId}", HttpMethod.PUT, entity, Record.class, 1);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void deleteRecord() {
        template.withBasicAuth("user1", "123")
                .delete("/records/{recordId}", 1);
    }
}