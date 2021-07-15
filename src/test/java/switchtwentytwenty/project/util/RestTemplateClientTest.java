package switchtwentytwenty.project.util;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class RestTemplateClientTest {

    @Test
    void restTemplateTest() {
        RestTemplateClient restTemplateClient = new RestTemplateClient();

        RestTemplate result = restTemplateClient.restTemplate();

        assertNotNull(result);
    }
}