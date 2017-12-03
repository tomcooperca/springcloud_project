package io.pivotal.training.fortune;

import io.pivotal.training.FortuneApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = FortuneApplication.class)
public class FortuneControllerTest {

    RestTemplate restTemplate;

    @Before
    public void init() {
        restTemplate = new RestTemplateBuilder().build();
    }

    @Test
    public void shouldReturnFortune() {
        ResponseEntity<Map> response = restTemplate.getForEntity("http://localhost:8081/", Map.class);
        response.getStatusCode().is2xxSuccessful();
        response.getBody().containsKey("fortune");
    }
}
