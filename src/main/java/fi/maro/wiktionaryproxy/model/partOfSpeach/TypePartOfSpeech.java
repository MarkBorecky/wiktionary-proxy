package fi.maro.wiktionaryproxy.model.partOfSpeach;

public enum TypePartOfSpeech {
    VERB, NOUN, ADJECTIVE;

    public static TypePartOfSpeech get(String name) throws IllegalArgumentException {
        return TypePartOfSpeech.valueOf(name.toUpperCase());
    }
}
