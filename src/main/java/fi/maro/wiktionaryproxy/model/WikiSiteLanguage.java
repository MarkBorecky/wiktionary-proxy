package fi.maro.wiktionaryproxy.model;

import fi.maro.wiktionaryproxy.model.partOfSpeach.PartOfSpeech;

import java.util.List;

public record WikiSiteLanguage(PartOfSpeech partOfSpeech, List<String> newTranslations) {
}