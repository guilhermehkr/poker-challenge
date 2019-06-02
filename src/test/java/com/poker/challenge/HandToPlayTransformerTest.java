package com.poker.challenge;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.poker.challenge.CardUtil.createCards;
import static com.poker.challenge.FileReaderTest.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HandToPlayTransformerTest {

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

        assertThat(play.getPlayerOneCards(), is(empty()));
        assertThat(play.getPlayerTwoCards(), is(empty()));
        assertThat("The given hand should be invalid", play.isInvalidHand());
    }

    @DataProvider
    public Object[][] validHands() {
        return new Object[][]{
                {
                        FIRST_HAND,
                        createCards("9C", "9D", "8D", "7C", "3C"),
                        createCards("2S", "KD", "TH", "9H", "8H")
                },
                {
                        SECOND_HAND,
                        createCards("6C", "5H", "AS", "4H", "7S"),
                        createCards("2S", "KD", "7H", "2C", "AC")
                }
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