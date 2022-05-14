package fi.maro.wiktionaryproxy.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WikiSite {
    private String keyWord;
    private Map<String, List<String>> translations;

    public WikiSite(String keyWord) {
        this.keyWord = keyWord;
        this.translations = new HashMap<>();
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void addTranslations(String lang, List<String> newTranslations) {
        this.translations.put(lang, newTranslations);
    }

    public List<String> getLanguage(String lang) {
        return translations.get(lang);
    }

    public Map<String, List<String>> getTranslations() {
        return translations;
    }

    public Set<String> getLanguages() {
        return translations.keySet();
    }
}
