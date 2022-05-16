package fi.maro.wiktionaryproxy.model.partOfSpeach;

public enum TypePartOfSpeach {
    VERB, NOUN, ADJECTIVE;

    public static TypePartOfSpeach get(String name) {
        return TypePartOfSpeach.valueOf(name.toUpperCase());
    }
}
