package io.pivotal.training.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class FortuneServiceClient {

    @Value("${fortuneService.baseUrl}")
    String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    public String getFortune() {
        Map fortune = restTemplate.getForObject(baseUrl, Map.class);
        return (String) fortune.get("fortune");
    }
}
