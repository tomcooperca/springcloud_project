package io.pivotal.training.greeting;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient(value = "http://fortune")
public interface FortuneServiceFeignClient {

    @RequestMapping("/")
    Map<String, String> getFortune();
}
