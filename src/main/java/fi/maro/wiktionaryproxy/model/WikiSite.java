package fi.maro.wiktionaryproxy.model;

import fi.maro.wiktionaryproxy.model.partOfSpeach.PartOfSpeech;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WikiSite {
    private final String keyWord;
    private final Map<String, List<String>> translations;
    private final Map<String, WikiSiteLanguage> wikiSiteLanguages;

    public WikiSite(String keyWord) {
        this.keyWord = keyWord;
        this.translations = new HashMap<>();
        this.wikiSiteLanguages = new HashMap<>();
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void addTranslations(String lang, PartOfSpeech partOfSpeech, List<String> newTranslations) {
        this.translations.put(lang, newTranslations);
        this.wikiSiteLanguages.put(lang, new WikiSiteLanguage(partOfSpeech, newTranslations));
    }

    public List<String> getLanguage(String lang) {
        return translations.get(lang);
    }

    public Set<String> getLanguages() {
        return translations.keySet();
    }

    public WikiSiteLanguage getWikiLanguage(String lang) {
        return wikiSiteLanguages.get(lang);
    }
}
