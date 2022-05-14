package fi.maro.wiktionaryproxy.service;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static fi.maro.wiktionaryproxy.TestUtils.*;
import static fi.maro.wiktionaryproxy.service.WiktionaryService.*;
import static org.assertj.core.api.Assertions.*;

class WiktionaryServiceTest {

    static WiktionaryService service;

    @BeforeAll
    public static void setUp() {
        service = new WiktionaryService();
    }

    @Test
    public void shouldReturnPageContent() {
        var result = service.getPageContent("koira")
                .map(Document::title)
                .orElse(StringUtils.EMPTY);
        assertThat(result).isEqualTo("koira - Wiktionary");
    }

    @Test
    public void shouldParseKoiraDocument() {
        var doc = readDocumentFromFile("koira.html");
        var wikiSite = parse(doc, "Finnish");
        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(wikiSite.getKeyWord()).isEqualTo("koira");
            soft.assertThat(wikiSite.getLanguages()).contains("Finnish", "Ingrian", "Karelian", "Votic");
            soft.assertThat(wikiSite.getLanguage("Finnish"))
                    .contains("dog", "dog paddle (swimming stroke)", "(military slang) military police");
        });
    }

}