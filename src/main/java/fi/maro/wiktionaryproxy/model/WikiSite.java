package fi.maro.wiktionaryproxy.model;

import fi.maro.wiktionaryproxy.model.partOfSpeach.TypePartOfSpeach;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WikiSite {
    private String keyWord;
    private Map<String, List<String>> translations;
    private Map<String, WikiSiteLanguage> wikiSiteLanugages;

    public WikiSite(String keyWord) {
        this.keyWord = keyWord;
        this.translations = new HashMap<>();
        this.wikiSiteLanugages = new HashMap<>();
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void addTranslations(String lang, TypePartOfSpeach partOfSpeech, List<String> newTranslations) {
        this.translations.put(lang, newTranslations);
        this.wikiSiteLanugages.put(lang, new WikiSiteLanguage(partOfSpeech, newTranslations));
    }

    public List<String> getLanguage(String lang) {
        return translations.get(lang);
    }

    public Set<String> getLanguages() {
        return translations.keySet();
    }

    public WikiSiteLanguage getWikiLanguage(String lang) {
        return wikiSiteLanugages.get(lang);
    }
}
