package com.example.juridov.my_app_run.authorization;

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
public class SecuredRestTemplateIntegrationTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testUnauthorizedFailWith401() throws Exception {
        ResponseEntity<String> result = template.getForEntity("/records", String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    @Test
    public void testAuthorizedSucceedWith200() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("user1", "123")
                .getForEntity("/records", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}