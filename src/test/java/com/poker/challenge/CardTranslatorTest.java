package com.poker.challenge;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.poker.challenge.CardTranslator.*;
import static com.poker.challenge.CardUtil.createCards;
import static com.poker.challenge.PlayBuilder.aPlay;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class CardTranslatorTest {

    private final static String TWO = "2";
    private final static String THREE = "3";
    private final static String SEVEN = "7";

    private CardTranslator testInstance;

    @BeforeMethod
    public void setup() {
        testInstance = new CardTranslator();
    }

    //@Test
    public void shouldTranslateCardsWhenRawCardsAreGiven() {

        List<Card> rawPlayerOneCards = createCards(ACE, JACK, KING, QUEEN, TEN);
        List<Card> expectedPlayerOneTranslation = createCards(TEN_NUMBER, JACK_NUMBER, QUEEN_NUMBER, KING_NUMBER, ACE_NUMBER);

        List<Card> rawPlayerTwoCards = createCards(TWO, JACK, SEVEN, THREE, KING);
        List<Card> expectedPlayerTwoTranslation = createCards(TWO, THREE, SEVEN, JACK_NUMBER, KING_NUMBER);

        Play translatedCards = testInstance.translate(
                aPlay()
                        .withPlayerOneCards(rawPlayerOneCards)
                        .withPlayerTwoCards(rawPlayerTwoCards)
                        .build()
        );

        assertThat(translatedCards.getPlayerOneCards(), contains(expectedPlayerOneTranslation.toArray()));
        assertThat(translatedCards.getPlayerTwoCards(), contains(expectedPlayerTwoTranslation.toArray()));
    }
}