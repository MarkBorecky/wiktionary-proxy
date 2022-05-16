package fi.maro.wiktionaryproxy.model.grammarForms.tense;

import java.util.Arrays;

public enum Tense {
    PRESENT("present"),
    PAST("past"),
    PERFECT("perfect"),
    PLUSQUAMPERFECT("pluperfect");


    private final String alternativeName;

    Tense(String alternativeName) {
        this.alternativeName = alternativeName;
    }

    public static Tense get(String text) {
        return Arrays.stream(Tense.values())
                .filter(tense -> tense.alternativeName.equals(text))
                .findFirst()
                .orElseThrow();
    }
}
