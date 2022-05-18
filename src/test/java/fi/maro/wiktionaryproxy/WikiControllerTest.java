package fi.maro.wiktionaryproxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WikiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldSearchWithDefaultLanguage() {
        assertThat(restTemplate.getForObject("http://localhost:%d/api/v1/search?word=koira".formatted(port),
                String.class)).contains("{\"keyWord\":\"koira\",\"languages\":[\"Finnish\"]}");
    }

    @Test
    public void shouldSearchWithFinnish() {
        assertThat(restTemplate.getForObject("http://localhost:%d/api/v1/search?word=koira&lang=Finnish".formatted(port),
                String.class)).contains("{\"keyWord\":\"koira\",\"languages\":[\"Finnish\"]}");
    }

    @Test
    public void shouldSearchWithKarelian() {
        assertThat(restTemplate.getForObject("http://localhost:%d/api/v1/search?word=koira&lang=Karelian".formatted(port),
                String.class)).contains("{\"keyWord\":\"koira\",\"languages\":[\"Karelian\"]}");
    }

    @Test
    public void shouldReturnEmptyResponseForSwedish() {
        assertThat(restTemplate.getForObject("http://localhost:%d/api/v1/search?word=koira&lang=Swedish".formatted(port),
                String.class)).contains("{\"keyWord\":\"koira\",\"languages\":[]}");
    }
}
