package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.card.Card;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.round.RoundResult.*;
import static com.poker.challenge.round.card.CardBuilder.aCard;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HighCardTieBreakerTest {

    private HighCardTieBreaker testInstance;

    @BeforeMethod
    public void setup() {
        testInstance = new HighCardTieBreaker();
    }

    @DataProvider
    public Object[][] playerCards() {
        return new Object[][]{
                {
                        newArrayList(
                                aCard().withValue("10").build(),
                                aCard().withValue("1").build(),
                                aCard().withValue("2").build(),
                                aCard().withValue("6").build(),
                                aCard().withValue("5").build()
                        ),
                        newArrayList(
                                aCard().withValue("10").build(),
                                aCard().withValue("1").build(),
                                aCard().withValue("2").build(),
                                aCard().withValue("6").build(),
                                aCard().withValue("5").build()
                        ),
                        Tie
                },
                {
                        newArrayList(
                                aCard().withValue("10").build(),
                                aCard().withValue("2").build(),
                                aCard().withValue("3").build(),
                                aCard().withValue("6").build(),
                                aCard().withValue("5").build()
                        ),
                        newArrayList(
                                aCard().withValue("10").build(),
                                aCard().withValue("1").build(),
                                aCard().withValue("3").build(),
                                aCard().withValue("6").build(),
                                aCard().withValue("5").build()
                        ),
                        PlayerOne
                },
                {
                        newArrayList(
                                aCard().withValue("10").build(),
                                aCard().withValue("1").build(),
                                aCard().withValue("3").build(),
                                aCard().withValue("6").build(),
                                aCard().withValue("5").build()
                        ),
                        newArrayList(
                                aCard().withValue("10").build(),
                                aCard().withValue("2").build(),
                                aCard().withValue("3").build(),
                                aCard().withValue("6").build(),
                                aCard().withValue("5").build()
                        ),
                        PlayerTwo
                },
                {
                        newArrayList(
                                aCard().withValue("10").build(),
                                aCard().withValue("1").build(),
                                aCard().withValue("3").build(),
                                aCard().withValue("6").build(),
                                aCard().withValue("5").build()
                        ),
                        newArrayList(
                                aCard().withValue("10").build(),

                                aCard().withValue("3").build(),
                                aCard().withValue("6").build(),
                                aCard().withValue("5").build()
                        ),
                        PlayerOne
                }
        };
    }

    @Test(dataProvider = "playerCards")
    public void shouldBreakTheTieAndReturnTheWinner(List<Card> playerOneCards, List<Card> playerTwoCards, RoundResult expectedRoundResult) {

        RoundResult roundResult = testInstance.breakTie(playerOneCards, playerTwoCards);
        assertThat(roundResult, is(expectedRoundResult));
    }
}