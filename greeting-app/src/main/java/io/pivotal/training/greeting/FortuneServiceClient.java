package io.pivotal.training.greeting;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class FortuneServiceClient {

    public static final Logger log = LoggerFactory.getLogger(FortuneServiceClient.class);

    @Value("${fortuneService.baseUrl}")
    String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultFortune")
    public String getFortune() {
        Map fortune = restTemplate.getForObject(baseUrl, Map.class);
        return (String) fortune.get("fortune");
    }

    public String defaultFortune() {
        log.info("Default fortune used");
        return "Your future is uncertain";
    }
}
