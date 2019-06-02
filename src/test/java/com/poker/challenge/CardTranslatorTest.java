package com.poker.challenge;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.Card.newCard;
import static com.poker.challenge.CardBuilder.aCard;
import static com.poker.challenge.CardTranslator.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class CardTranslatorTest {

    private CardTranslator testInstance;

    @BeforeMethod
    public void setup() {
        testInstance = new CardTranslator();
    }

    @Test
    public void should() {

        List<Card> playOneCards = newArrayList(
                aCard().withValue(ACE).build(),
                aCard().withValue(JACK).build(),
                aCard().withValue(KING).build(),
                aCard().withValue(QUEEN).build(),
                aCard().withValue(TEN).build()
        );

        List<Card> playTwoCards = newArrayList(
                aCard().withValue("2").build(),
                aCard().withValue(JACK).build(),
                aCard().withValue("7").build(),
                aCard().withValue("3").build(),
                aCard().withValue(KING).build()
        );

        Play play = new Play(playOneCards, playTwoCards);

        Play translateCards = testInstance.translate(play);

        assertThat(translateCards.getPlayerOneCards(), contains(TEN_NUMBER, JACK_NUMBER, QUEEN_NUMBER, KING_NUMBER, ACE_NUMBER));
        assertThat(translateCards.getPlayerTwoCards(), contains("2", "3", "7", JACK_NUMBER, KING_NUMBER));
    }
}