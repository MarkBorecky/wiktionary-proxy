package fi.maro.wiktionaryproxy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestUtils {

    public static final String PATH_TEST_PATTERN = "src/test/resources/%s";

    public static Document readDocumentFromFile(String documentName) {
        try {
            var stringPath = PATH_TEST_PATTERN.formatted(documentName);
            var path = Path.of(stringPath);
            var html = Files.readString(path);
            return Jsoup.parseBodyFragment(html);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
