package fi.maro.wiktionaryproxy.model.partOfSpeach;

import org.junit.jupiter.api.Test;

import static fi.maro.wiktionaryproxy.model.partOfSpeach.TypePartOfSpeech.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TypePartOfSpeechTest {

    @Test
    public void shouldReturnVerb() {
        assertThat(get("Verb")).isEqualTo(VERB);
    }

    @Test
    public void shouldReturnVerb2() {
        assertThat(get("verb")).isEqualTo(VERB);
    }

    @Test
    public void shouldReturnVerb3() {
        assertThat(get("VERB")).isEqualTo(VERB);
    }

    @Test
    public void shouldReturnNoun() {
        assertThat(get("Noun")).isEqualTo(NOUN);
    }

    @Test
    public void shouldReturnNoun2() {
        assertThat(get("noun")).isEqualTo(NOUN);
    }

    @Test
    public void shouldReturnNoun3() {
        assertThat(get("NOUN")).isEqualTo(NOUN);
    }

    @Test
    public void shouldReturnAdjective() {
        assertThat(get("Adjective")).isEqualTo(ADJECTIVE);
    }

    @Test
    public void shouldReturnAdjective2() {
        assertThat(get("adjective")).isEqualTo(ADJECTIVE);
    }

    @Test
    public void shouldReturnAdjective3() {
        assertThat(get("ADJECTIVE")).isEqualTo(ADJECTIVE);
    }

    @Test
    public void shouldThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> get("NonExistingPartOfSpeech"));
        var expectedMessage = "No enum constant fi.maro.wiktionaryproxy.model.partOfSpeach.TypePartOfSpeech.NONEXISTINGPARTOFSPEECH";
        var actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

}