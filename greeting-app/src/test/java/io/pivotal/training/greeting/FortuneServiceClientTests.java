package io.pivotal.training.greeting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(workOffline = true, ids = "io.pivotal.training.springcloud:fortune-service:+:stubs:8081")
public class FortuneServiceClientTests {

    @Autowired
    FortuneServiceClient fortuneServiceClient;

    public static final String expectedFortune = "my test fortune";

    @Test
    public void shouldReturnAFortune() {
        assertThat(fortuneServiceClient.getFortune()).isEqualTo(expectedFortune);
    }
}
