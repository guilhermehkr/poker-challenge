package com.poker.challenge.round.card;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.poker.challenge.round.card.CardBuilder.aCard;
import static org.hamcrest.Matchers.is;

public class CardBuilderTest {

    @DataProvider
    public Object[][] stringCards() {
        return new Object[][]{
                {"2D", aCard().withValue("2").withSuit("D").build()},
                {"10H", aCard().withValue("10").withSuit("H").build()},
                {"2S", aCard().withValue("2").withSuit("S").build()},
                {"13H", aCard().withValue("13").withSuit("H").build()},
                {"4C", aCard().withValue("4").withSuit("C").build()},
                {"6C", aCard().withValue("6").withSuit("C").build()},
                {"7C", aCard().withValue("7").withSuit("C").build()},
                {"11S", aCard().withValue("11").withSuit("S").build()},
                {"14H", aCard().withValue("14").withSuit("H").build()},
                {"13S", aCard().withValue("13").withSuit("S").build()}
        };
    }

    @Test(dataProvider = "stringCards")
    public void shouldReceiveStringCardAndReturnCard(String stringCard, Card card) {
        Card cardFromString = aCard()
                .withStringCard(stringCard)
                .build();

        MatcherAssert.assertThat(cardFromString, is(card));
    }
}