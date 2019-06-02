package com.poker.challenge;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.Card.newCard;
import static com.poker.challenge.FileReaderTest.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HandToPlayTransformerTest {

    public final static List<Card> FIRST_HAND_PLAYER_ONE_CARDS =
            newArrayList(newCard("9C"), newCard("9D"), newCard("8D"), newCard("7C"), newCard("3C"));

    public final static List<Card> FIRST_HAND_PLAYER_TWO_CARDS =
            newArrayList(newCard("2S"), newCard("KD"), newCard("TH"), newCard("9H"), newCard("8H"));

    public final static List<Card> SECOND_HAND_PLAYER_ONE_CARDS =
            newArrayList(newCard("9C"), newCard("9D"), newCard("8D"), newCard("7C"), newCard("3C"));

    public final static List<Card> SECOND_HAND_PLAYER_TWO_CARDS =
            newArrayList(newCard("2S"), newCard("KD"), newCard("TH"), newCard("9H"), newCard("8H"));

    private HandToPlayTransformer testInstance;

    @BeforeMethod
    public void setup() {
        testInstance = new HandToPlayTransformer();
    }

    @DataProvider
    public Object[][] invalidHands() {
        return new Object[][]{
                {null},
                {EMPTY},
                {FIRST_HAND_WHITESPACE},
                {SECOND_HAND_WHITESPACE}
        };
    }

    @Test(dataProvider = "invalidHands")
    public void shouldReturnAnInvalidHandGivenHandWithMoreThan10cardsInSet(String invalidHand) {

        Play play = testInstance.transform(invalidHand);

        assertThat(play.getPlayerOneCards(), is(nullValue()));
        assertThat(play.getPlayerTwoCards(), is(nullValue()));
        assertThat("The given hand should be invalid", play.isInvalidHand());
    }

    @DataProvider
    public Object[][] validHands() {
        return new Object[][]{
                {FIRST_HAND, FIRST_HAND_PLAYER_ONE_CARDS, FIRST_HAND_PLAYER_TWO_CARDS},
                {SECOND_HAND, SECOND_HAND_PLAYER_ONE_CARDS, SECOND_HAND_PLAYER_TWO_CARDS}
        };
    }

    @Test(dataProvider = "validHands")
    public void shouldReturnPlayPopulatedWithCards(String hand, List<Card> playerOneCards, List<Card> playerTwoCards) {

        Play play = testInstance.transform(hand);

        assertThat(play.getPlayerOneCards(), equalTo(playerOneCards));
        assertThat(play.getPlayerTwoCards(), equalTo(playerTwoCards));
        assertThat("The given hand should be valid", play.isValidHand());
    }
}