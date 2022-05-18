package fi.maro.wiktionaryproxy.model;

import fi.maro.wiktionaryproxy.model.partOfSpeach.Adjective;
import fi.maro.wiktionaryproxy.model.partOfSpeach.Noun;
import fi.maro.wiktionaryproxy.model.partOfSpeach.Verb;
import org.junit.jupiter.api.Test;

import static fi.maro.wiktionaryproxy.model.PartOfSpeechMapper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PartOfSpeechMapperTest {

    @Test
    public void shouldReturnVerb() {
        assertThat(map("Verb")).isInstanceOf(Verb.class);
    }

    @Test
    public void shouldReturnNoun() {
        assertThat(map("Noun")).isInstanceOf(Noun.class);
    }

    @Test
    public void shouldReturnAdjective() {
        assertThat(map("Adjective")).isInstanceOf(Adjective.class);
    }

    @Test
    public void shouldThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> map("NonExistingPartOfSpeech"));
        var expectedMessage = "There is no part of speech like NonExistingPartOfSpeech.";
        var actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}