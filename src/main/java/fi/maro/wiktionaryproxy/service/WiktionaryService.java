package fi.maro.wiktionaryproxy.service;

import fi.maro.wiktionaryproxy.model.WikiSite;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

import static fi.maro.wiktionaryproxy.model.WikiSiteMapper.map;

@Service
public class WiktionaryService {

    public static final String URL_PATTERN = "https://en.wiktionary.org/wiki/%s";

    public WikiSite search(String word, String lang) {
        return getPageContent(word)
                .map(document -> parse(document, lang))
                .orElseThrow();
    }

    public static WikiSite parse(Document document) {
        return map(document, StringUtils.EMPTY);
    }

    public static WikiSite parse(Document document, String lang) {
        return map(document, lang);
    }

    Optional<Document> getPageContent(String keyWord) {
        String url = URL_PATTERN.formatted(keyWord);
        Document document = getDocument(url);
        return Optional.ofNullable(document);
    }

    private Document getDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
