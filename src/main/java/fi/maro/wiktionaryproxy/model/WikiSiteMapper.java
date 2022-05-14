package fi.maro.wiktionaryproxy.model;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Optional;

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

    public static WikiSite map(Document document, String lang) {
        var title = getWikiSiteTitle(document);
        var content = getContent(document);
        return createWikiSite(title, content, lang);
    }

    private static Elements getContent(Document document) {
        return document.getElementsByClass(CONTENT_CLASS_NAME)
                .stream().findFirst().orElseThrow().children();
    }

    private static WikiSite createWikiSite(String title, Elements elements, String lang) {
        var currentLanguage = StringUtils.EMPTY;
        var currentParagraph = StringUtils.EMPTY;
        var currentSubParagraph = StringUtils.EMPTY;

        var site = new WikiSite(title);

        for (Element child : elements) {
            if (isTagNameEquals(child, H2)) {
                currentLanguage = child.getElementsByClass(HEADLINE).text();
                currentSubParagraph = StringUtils.EMPTY;
                currentParagraph = StringUtils.EMPTY;
            }
            if (isTagNameEquals(child, H3)) {
                currentParagraph = child.getElementsByClass(HEADLINE).get(0).text();
                currentSubParagraph = StringUtils.EMPTY;
            }
            if (isTagNameEquals(child, H4)) {
                currentSubParagraph = child.getElementsByClass(HEADLINE).get(0).text();
            }
            if (isTagNameEquals(child, P)) {
                currentSubParagraph = child.getElementsByTag(STRONG).text();
            }
            if (areTheyTranslations(currentParagraph, currentSubParagraph, title, child)) {
                if (lang.isBlank() || lang.equals(currentLanguage)) {
                    site.addTranslations(currentLanguage, getTextFromList(child));
                }
            }
        }
        return site;
    }

    private static boolean areTheyTranslations(String currentParagraph, String currentSubParagraph, String title, Element child) {
        return "Noun".equals(currentParagraph) && currentSubParagraph.equals(title) && child.getElementsByTag("li").size() > 0;
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
                .orElse(StringUtils.EMPTY);
    }
}
