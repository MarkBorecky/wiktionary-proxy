package fi.maro.wiktionaryproxy.model;

import fi.maro.wiktionaryproxy.model.partOfSpeach.TypePartOfSpeach;

import java.util.List;

public record WikiSiteLanguage(TypePartOfSpeach partOfSpeech, List<String> newTranslations) {
}