package fi.maro.wiktionaryproxy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = WiktionaryProxyApplication.class)
class WiktionaryProxyApplicationTests {


    @Test
    public void applicationContextTest() {
        WiktionaryProxyApplication.main(new String[]{});
    }

}
