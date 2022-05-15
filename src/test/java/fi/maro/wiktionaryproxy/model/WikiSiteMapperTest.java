package fi.maro.wiktionaryproxy.model;

import org.junit.jupiter.api.Test;

import static fi.maro.wiktionaryproxy.TestUtils.readDocumentFromFile;
import static fi.maro.wiktionaryproxy.model.WikiSiteMapper.*;
import static fi.maro.wiktionaryproxy.model.WikiSiteMapper.getWikiSiteTitle;
import static org.assertj.core.api.Assertions.*;

class WikiSiteMapperTest {

    @Test
    public void shouldReturnComponentTitle() {
        var doc = readDocumentFromFile("koira.html");
        var result = getWikiSiteTitle(doc);
        assertThat(result).isEqualTo("koira");
    }

    @Test
    public void shouldSimplifyText() {
        var input = "ąćęöä";
        var result = simplify(input);
        assertThat(result).isEqualTo("aceoa");
    }

}