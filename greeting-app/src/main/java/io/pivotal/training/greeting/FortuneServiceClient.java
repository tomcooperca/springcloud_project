package io.pivotal.training.greeting;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@AllArgsConstructor
public class FortuneServiceClient {

    DiscoveryClient discoveryClient;
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultFortune")
    public String getFortune() {
        String baseUrl = lookupUrlFor("FORTUNE");
        Map fortune = restTemplate.getForObject(baseUrl, Map.class);
        return (String) fortune.get("fortune");
    }

    public String defaultFortune() {
        log.info("Default fortune used");
        return "Your future is uncertain";
    }

    private String lookupUrlFor(String app) {
        List<ServiceInstance> instances = discoveryClient.getInstances(app);
        return instances.stream()
                .findFirst()
                .map(ServiceInstance::getUri)
                .map(URI::toString)
                .orElseThrow(RuntimeException::new);
    }
}
