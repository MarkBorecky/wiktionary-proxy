package fi.maro.wiktionaryproxy.service;

import fi.maro.wiktionaryproxy.model.partOfSpeach.Noun;
import fi.maro.wiktionaryproxy.model.partOfSpeach.Verb;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static fi.maro.wiktionaryproxy.TestUtils.readDocumentFromFile;
import static fi.maro.wiktionaryproxy.service.WiktionaryService.parse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WiktionaryServiceTest {

    public static final String FINNISH = "Finnish";
    static WiktionaryService service;

    @BeforeAll
    public static void setUp() {
        service = new WiktionaryService();
    }

    @Test
    public void shouldReturnPageContent() {
        var result = service.getPageContent("koira")
                .map(Document::title)
                .orElse(StringUtils.EMPTY);
        assertThat(result).isEqualTo("koira - Wiktionary");
    }

    @Test
    public void shouldThrowException() {
        Exception exception = assertThrows(NoSuchElementException.class,
                () -> service.search("khsdkfhskfd", "dfsf"));
        var expectedMessage = "No value present";
        var actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }


    @Test
    public void shouldParseKoiraDocument() {
        var doc = readDocumentFromFile("koira.html");
        var wikiSite = parse(doc);
        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(wikiSite.getKeyWord()).isEqualTo("koira");
            soft.assertThat(wikiSite.getLanguages()).contains(FINNISH, "Ingrian", "Karelian", "Votic");
            soft.assertThat(wikiSite.getLanguage(FINNISH))
                    .contains("dog", "dog paddle (swimming stroke)", "(military slang) military police");
            soft.assertThat(wikiSite.getWikiLanguage(FINNISH).partOfSpeech()).isExactlyInstanceOf(Noun.class);
        });
    }
    @Test
    public void shouldParseKoiraDocumentOnlyFinnish() {
        var doc = readDocumentFromFile("koira.html");
        var wikiSite = parse(doc, FINNISH);
        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(wikiSite.getKeyWord()).isEqualTo("koira");
            soft.assertThat(wikiSite.getLanguages()).contains(FINNISH);
            soft.assertThat(wikiSite.getLanguage(FINNISH))
                    .contains("dog", "dog paddle (swimming stroke)", "(military slang) military police");
            soft.assertThat(wikiSite.getWikiLanguage(FINNISH).partOfSpeech()).isExactlyInstanceOf(Noun.class);
        });
    }

    @Test
    public void shouldParseOllaDocument() {
        var doc = readDocumentFromFile("olla.html");
        var wikiSite = parse(doc);
        SoftAssertions.assertSoftly(soft -> {
//            soft.assertThat(wikiSite.getKeyWord()).isEqualTo("olla");
//            soft.assertThat(wikiSite.getLanguages())
//                    .contains("English", "Aragonese", "Catalan", "Estonian", "Finnish", "Icelandic", "Ingrian", "Irish",
//                            "Italian", "Karelian", "Latin", "Livvi", "Middle Irish", "Old Dutch", "Old Norse",
//                            "Old Spanish", "Spanish", "Swedish");
//            soft.assertThat(wikiSite.getLanguage("Finnish"))
//                    .contains(
//                            "(copulative) to be (indicating that the subject and the complement of the verb form the same thing) Min?? olen onnellinen. ??? I am happy. Min?? olen l????k??ri. ??? I am a doctor.",
//                            "(intransitive) to be (occupy a place) Min?? olen kotona. ??? I am at home.",
//                            "(copulative) to be, constitute, make up, form Tuo on maanpetos. ??? That constitutes (high) treason.",
//                            "(intransitive, adessive + 3rd person singular + ~) to have; to own, to possess Minulla on/oli/on ollut kissa (nom.). (when in positive sentences, countable nouns in nominative) ??? I have/had/have had a cat. Minulla ei ole kissaa (part.). (when in negative sentences, countable nouns in partitive) ??? I don't have a cat. Minulla on/oli/on ollut sinut (acc.). (when in positive sentences, personal pronouns in accusative) ??? I have/had/have had you. Minulla ei ole/ei ollut/ei ole ollut sinua (part.). (when in negative sentences, personal pronouns in partitive) ??? I don't have/didn't have/haven't had you. Synonyms: (to own) omistaa, (discouraged in most cases) omata",
//                            "(intransitive, inessive + 3rd person singular + ~) to have, to possess (as a feature or capability, as opposed to simple possession; almost always for inanimate subjects) T??ss?? autossa on kaikki lis??varusteet. ??? This car has all the accessories.",
//                            "(intransitive, ~ (olemassa)) to exist (the subject often indefinite = in partitive case -> verb in 3rd-pers. singular) Rakkautta ei ole (olemassa). ??? Love doesn't exist.",
//                            "(intransitive) to behave, act (as if...) (when followed by a essive plural form of a present active participle with possessive suffix, or a subordinate clause beginning with (ik????n,) kuin, requiring conditional mood) H??n oli (ik????n), kuin ei olisi huomannut mit????n. H??n ei ollut huomaavinaan mit????n. ??? He behaved as if he hadn't noticed anything.",
//                            "(transitive, auxiliary) to have (a verb to build active present perfect tense and active past perfect tense, taking active past participle, ending -nut/-nyt (singular) or -neet (pl.)) Olen jo sy??nyt t??n????n. ??? I have already eaten today. Olemme jo sy??neet t??n????n. ??? We have already eaten today. Olin jo sy??nyt. ??? I had already eaten. Olimme jo sy??neet. ??? We had already eaten.",
//                            "(transitive, auxiliary) to have (a verb to build impersonal simple past tense, impersonal passive present perfect tense and impersonal passive past perfect tense, taking passive past participle, ending -tu/-ty) Aamiainen oli jo sy??ty. ??? Breakfast had already been eaten. Minulla on/ei ole rahaa (part.). (singular uncountable nouns in partitive in both positive and negative sentences) ??? I have/don't have money. Minulla on valta (nom.). (with an uncountable noun in nominative, the meaning or nuance of the sentence changes) ??? I've got the power.",
//                            "(intransitive, 3rd person singular) (there) be P??yd??ll?? on kissa. (when in positive sentences, singular countable nouns in nominative) ??? There is a cat on the table. P??yd??ll?? ei ole kissaa. (when in negative sentences, singular countable nouns in partitive) ??? There is no cat on the table. P??yd??ll?? on/ei ole kissoja. (plural countable nouns in partitive in both positive and negative sentences) ??? There are (some) cats / There are no cats on the table. Lattialla on/ei ole rahaa. (singular uncountable nouns in partitive in both positive and negative sentences) ??? There is (some) / There is no money on the floor.",
//                            "(intransitive, + genitive + 3rd person singular + passive present participle) to have to do something, must do something; be obliged/forced to do something Minun (gen.) on nyt ment??v??. ??? I have to go now. Minun on palautettava kirja kirjastoon perjantaihin menness??. ??? I have to return the book to the library by Friday. that same in passive: nominative/accusative + 3rd-pers. singular + passive present participle, -tava/-t??v?? = to have to be done, must be done. Kirja (nom.) on palautettava kirjastoon perjantaihin menness??. (countable nouns in nominative) ??? The book has to be returned to the library by Friday. Onko sinut (acc.) hiljennettev?? pakolla? (personal pronouns in accusative) ??? Do I have to make you shut your mouth? (literally, ???Do you have to be quietened by force????)",
//                            "(transitive) to play a children's game Synonym: leikki?? 2013, Tea Hiilloste (lyrics), ???Halihippa???, performed by Tea: S?? ja m?? ollaan halihippaa, yks ja kaks, toinen meist?? karkaa... You and I are playing hug tag, one and two, one of us runs away...",
//                            "2013, Tea Hiilloste (lyrics), ???Halihippa???, performed by Tea: S?? ja m?? ollaan halihippaa, yks ja kaks, toinen meist?? karkaa... You and I are playing hug tag, one and two, one of us runs away..."
//                    );
            soft.assertThat(wikiSite.getWikiLanguage(FINNISH).partOfSpeech()).isExactlyInstanceOf(Verb.class);
//            soft.assertThat(wikiSite.getWikiLanguage(FINNISH).partOfSpeech()).isEqualTo(VERB);
        });
    }
}