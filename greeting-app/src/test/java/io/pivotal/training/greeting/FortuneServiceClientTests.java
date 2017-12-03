package io.pivotal.training.greeting;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class FortuneServiceClientTests {

    @Autowired
    FortuneServiceClient fortuneServiceClient;

    public static final String expectedFortune = "my test fortune";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8081));

    @Before
    public void setup() {
        stubFor(WireMock.get(urlEqualTo("/"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(String.format("{\"fortune\": \"%s\"}", expectedFortune))
                ));
    }

    @Test
    public void shouldReturnAFortune() {
        assertThat(fortuneServiceClient.getFortune()).isEqualTo(expectedFortune);
    }
}
