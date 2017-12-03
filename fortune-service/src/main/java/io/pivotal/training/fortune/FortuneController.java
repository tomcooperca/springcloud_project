package io.pivotal.training.fortune;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FortuneController {

    private final Logger log = LoggerFactory.getLogger(FortuneController.class);

    @Autowired
    FortuneService service;

    @GetMapping("/")
    public Map<String, String> getFortune() {
        String fortune = service.getFortune();
        log.info("retrieving fortune {}", fortune);

        Map<String, String> map = new HashMap<>();
        map.put("fortune", fortune);
        return map;
    }
}
