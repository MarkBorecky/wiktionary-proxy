package fi.maro.wiktionaryproxy.model.grammarForms;

import fi.maro.wiktionaryproxy.model.grammarForms.mood.Mood;
import fi.maro.wiktionaryproxy.model.grammarForms.tense.Tense;

public class ConjugationForm extends Form {
    Person person;
    boolean isPositive;
    Mood mood;
    Tense tense;
}
