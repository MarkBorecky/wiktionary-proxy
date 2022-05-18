package fi.maro.wiktionaryproxy.model;

import fi.maro.wiktionaryproxy.model.partOfSpeach.Adjective;
import fi.maro.wiktionaryproxy.model.partOfSpeach.Noun;
import fi.maro.wiktionaryproxy.model.partOfSpeach.PartOfSpeech;
import fi.maro.wiktionaryproxy.model.partOfSpeach.Verb;

public class PartOfSpeechMapper {
    public static PartOfSpeech map(String partOfSpeech) {
        return switch (partOfSpeech) {
            case "Verb" -> new Verb();
            case "Noun" -> new Noun();
            case "Adjective" -> new Adjective();
            default -> throw new IllegalArgumentException("There is no part of speech like %s.".formatted(partOfSpeech));
        };
    }
}
