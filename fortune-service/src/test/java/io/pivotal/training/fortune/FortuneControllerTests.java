package io.pivotal.training.fortune;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class FortuneControllerTests {

    @Autowired
    FortuneController fortuneController;
    @MockBean
    private FortuneService fortuneService;
    private MockMvc mockMvc;

    public static final String expectedFortune = "my special fortune";

    @Before
    public void setup() {
        when(fortuneService.getFortune()).thenReturn(expectedFortune);
        mockMvc = MockMvcBuilders.standaloneSetup(fortuneController)
                .build();
    }

    @Test
    public void shouldGetFortuneOverHttp() throws Exception {
        mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.fortune").value(expectedFortune));

        verify(fortuneService).getFortune();
    }

}
