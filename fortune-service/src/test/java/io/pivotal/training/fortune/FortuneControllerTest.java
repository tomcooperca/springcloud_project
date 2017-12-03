package io.pivotal.training.fortune;

import io.pivotal.training.FortuneApplication;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FortuneApplication.class)
@Ignore
public class FortuneControllerTest {

    @LocalServerPort
    int port;

    RestTemplate restTemplate;
    String url;

    @Before
    public void init() {
        restTemplate = new RestTemplate();
        url = "http://localhost:" + port;
    }

    @Test
    public void shouldReturnFortune() {
        ResponseEntity<Map> response = restTemplate.getForEntity(url + "/", Map.class);
        response.getStatusCode().is2xxSuccessful();
        response.getBody().containsKey("fortune");
    }
}
