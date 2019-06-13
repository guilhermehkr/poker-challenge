package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.combination.Rank;
import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.card.Card;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.combination.Rank.HighCard;
import static com.poker.challenge.combination.Rank.Straight;
import static com.poker.challenge.round.RoundResult.*;
import static com.poker.challenge.round.card.CardBuilder.aCard;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StraightTieBreakerTest extends BaseTieBreakerTest {

    private HighCardTieBreaker testInstance;

    @BeforeMethod
    public void setup() {
        testInstance = new HighCardTieBreaker();
    }

    @Override
    protected TieBreaker getTestInstance() {
        return testInstance;
    }

    @Override
    protected Rank getRelatedRank() {
        return Straight;
    }

    @DataProvider
    public Object[][] playerCards() {
        return new Object[][]{
                {
                        newArrayList(
                                aCard().withStringCard("10S").build(),
                                aCard().withStringCard("1C").build(),
                                aCard().withStringCard("2D").build(),
                                aCard().withStringCard("6H").build(),
                                aCard().withStringCard("5S").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("10C").build(),
                                aCard().withStringCard("1D").build(),
                                aCard().withStringCard("2H").build(),
                                aCard().withStringCard("6S").build(),
                                aCard().withStringCard("5C").build()
                        ),
                        Tie
                },
                {
                        newArrayList(
                                aCard().withStringCard("7S").build(),
                                aCard().withStringCard("11C").build(),
                                aCard().withStringCard("4D").build(),
                                aCard().withStringCard("2H").build(),
                                aCard().withStringCard("8S").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("6C").build(),
                                aCard().withStringCard("1D").build(),
                                aCard().withStringCard("10H").build(),
                                aCard().withStringCard("9S").build(),
                                aCard().withStringCard("5C").build()
                        ),
                        PlayerOne
                },
                {
                        newArrayList(
                                aCard().withStringCard("3S").build(),
                                aCard().withStringCard("12C").build(),
                                aCard().withStringCard("4D").build(),
                                aCard().withStringCard("2H").build(),
                                aCard().withStringCard("6S").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("10C").build(),
                                aCard().withStringCard("6D").build(),
                                aCard().withStringCard("5H").build(),
                                aCard().withStringCard("2S").build(),
                                aCard().withStringCard("12C").build()
                        ),
                        PlayerTwo
                },
                {
                        newArrayList(
                                aCard().withStringCard("10D").build(),
                                aCard().withStringCard("2H").build(),
                                aCard().withStringCard("3S").build(),
                                aCard().withStringCard("6C").build(),
                                aCard().withStringCard("5D").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("10H").build(),
                                aCard().withStringCard("1H").build(),
                                aCard().withStringCard("4S").build(),
                                aCard().withStringCard("6C").build(),
                                aCard().withStringCard("5D").build()
                        ),
                        PlayerTwo
                },
                {
                        newArrayList(
                                aCard().withStringCard("10D").build(),
                                aCard().withStringCard("2H").build(),
                                aCard().withStringCard("3S").build(),
                                aCard().withStringCard("6C").build(),
                                aCard().withStringCard("5D").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("10H").build(),
                                aCard().withStringCard("1H").build(),
                                aCard().withStringCard("3S").build(),
                                aCard().withStringCard("6C").build(),
                                aCard().withStringCard("5D").build()
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