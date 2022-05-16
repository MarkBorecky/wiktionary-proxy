package fi.maro.wiktionaryproxy.model;

import fi.maro.wiktionaryproxy.model.partOfSpeach.TypePartOfSpeach;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class WikiSiteMapper {

    public static final String CONTENT_CLASS_NAME = "mw-parser-output";
    public static final String FIRST_HEADING = "firstHeading";
    public static final String HEADLINE = "mw-headline";
    public static final String H2 = "h2";
    public static final String H3 = "h3";
    public static final String H4 = "h4";
    public static final String P = "p";
    public static final String STRONG = "strong";
    public static final String LI = "li";
    public static final List<String> forbiddenClasses = List.of("disambig-see-also-2", "toc");
    public static final List<String> allowedParagraphs = List.of("Noun", "Verb", "Adjective");

    public static WikiSite map(Document document, String lang) {
        return createWikiSite(document, lang);
    }

    private static WikiSite createWikiSite(Document document, String lang) {
        var title = getWikiSiteTitle(document);
        Predicate<Element> predicate = element -> !forbiddenClasses.contains(element.className());
        var elements = document.getElementsByClass(CONTENT_CLASS_NAME)
                .stream().findFirst().orElseThrow().children().stream()
                .filter(predicate)
                .toList();

        var currentLanguage = EMPTY;
        var currentParagraph = EMPTY;
        var currentSubParagraph = EMPTY;

        var site = new WikiSite(title);

        for (Element child : elements) {
            if (isTagNameEquals(child, H2)) {
                currentLanguage = child.getElementsByClass(HEADLINE).text();
                currentSubParagraph = EMPTY;
                currentParagraph = EMPTY;
            }
            if (isTagNameEquals(child, H3)) {
                currentParagraph = child.getElementsByClass(HEADLINE).get(0).text();
                currentSubParagraph = EMPTY;
            }
            if (isTagNameEquals(child, H4)) {
                currentSubParagraph = child.getElementsByClass(HEADLINE).get(0).text();
            }
            if (isTagNameEquals(child, P)) {
                currentSubParagraph = getCurrentSubParagraph(child);
            }
            if (areTheyTranslations(currentParagraph, currentSubParagraph, title, child)) {
                if (lang.isBlank() || currentSubParagraphIsLikeTitle(lang, currentLanguage)) {
                    site.addTranslations(currentLanguage, TypePartOfSpeach.get(currentParagraph), getTextFromList(child));
                }
            }
        }
        return site;
    }

    private static String getCurrentSubParagraph(Element child) {
        return child.getElementsByTag(STRONG).stream()
                .findFirst()
                .map(Element::text)
                .orElse(EMPTY);
    }

    private static boolean areTheyTranslations(String currentParagraph, String currentSubParagraph, String title, Element child) {
        return allowedParagraphs.contains(currentParagraph)
                && currentSubParagraphIsLikeTitle(currentSubParagraph, title)
                && child.getElementsByTag("li").size() > 0;
    }

    private static boolean currentSubParagraphIsLikeTitle(String currentSubParagraph, String title) {
        return currentSubParagraph.equals(title) || simplify(currentSubParagraph).equals(simplify(title));
    }

    static String simplify(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    private static List<String> getTextFromList(Element child) {
        return child.getElementsByTag(LI).stream().map(Element::text).toList();
    }

    private static boolean isTagNameEquals(Element child, String check) {
        return child.tagName().equals(check);
    }

    static String getWikiSiteTitle(Document document) {
        return Optional.ofNullable(document.getElementById(FIRST_HEADING))
                .map(Element::text)
                .orElse(EMPTY);
    }
}
