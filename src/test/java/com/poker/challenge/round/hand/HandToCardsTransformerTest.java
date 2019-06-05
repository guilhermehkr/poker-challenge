package com.poker.challenge.round.hand;

import com.poker.challenge.round.card.Card;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.round.card.CardBuilder.aCard;
import static org.hamcrest.Matchers.contains;

public class HandToCardsTransformerTest {

    private HandToCardsTransformer testInstance;

    @BeforeMethod
    public void setup() {
        testInstance = new HandToCardsTransformer();
    }

    @DataProvider
    public Object[][] stringCards() {

        Object[] handOne = {
                "JS 7C 4C AH KS",
                newArrayList(
                        aCard().withStringCard("4C").build(),
                        aCard().withStringCard("7C").build(),
                        aCard().withStringCard("11S").build(),
                        aCard().withStringCard("13S").build(),
                        aCard().withStringCard("14H").build()
                )
        };

        Object[] handTwo = {
                "3D TH 2S QH 6C",
                newArrayList(
                        aCard().withStringCard("2S").build(),
                        aCard().withStringCard("3D").build(),
                        aCard().withStringCard("6C").build(),
                        aCard().withStringCard("10H").build(),
                        aCard().withStringCard("12H").build()
                )
        };

        return new Object[][]{
                handOne,
                handTwo
        };
    }

    @Test(dataProvider = "stringCards")
    public void shouldParseStringHandToListOfCardsAndSort(String stringHand, List<Card> expectedSortedCards) {
        List<Card> cards = testInstance.transform(stringHand);
        MatcherAssert.assertThat(cards, contains(expectedSortedCards.toArray()));
    }
}