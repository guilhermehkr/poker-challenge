package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.combination.Rank;
import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.card.Card;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.combination.Rank.Flush;
import static com.poker.challenge.combination.Rank.Straight;
import static com.poker.challenge.round.RoundResult.*;
import static com.poker.challenge.round.card.CardBuilder.aCard;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FlushTieBreakerTest extends HighCardTieBreakerTest {

    @Override
    protected Rank getRelatedRank() {
        return Flush;
    }

    @DataProvider
    public Object[][] playerCards() {
        return new Object[][]{
                {
                        newArrayList(
                                aCard().withStringCard("10S").build(),
                                aCard().withStringCard("1S").build(),
                                aCard().withStringCard("2S").build(),
                                aCard().withStringCard("6S").build(),
                                aCard().withStringCard("5S").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("10S").build(),
                                aCard().withStringCard("1S").build(),
                                aCard().withStringCard("2S").build(),
                                aCard().withStringCard("6S").build(),
                                aCard().withStringCard("5S").build()
                        ),
                        Tie
                },
                {
                        newArrayList(
                                aCard().withStringCard("7C").build(),
                                aCard().withStringCard("11C").build(),
                                aCard().withStringCard("4C").build(),
                                aCard().withStringCard("2C").build(),
                                aCard().withStringCard("8C").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("6C").build(),
                                aCard().withStringCard("1C").build(),
                                aCard().withStringCard("10C").build(),
                                aCard().withStringCard("9C").build(),
                                aCard().withStringCard("5C").build()
                        ),
                        PlayerOne
                },
                {
                        newArrayList(
                                aCard().withStringCard("3D").build(),
                                aCard().withStringCard("12D").build(),
                                aCard().withStringCard("4D").build(),
                                aCard().withStringCard("2D").build(),
                                aCard().withStringCard("6D").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("10D").build(),
                                aCard().withStringCard("6D").build(),
                                aCard().withStringCard("5D").build(),
                                aCard().withStringCard("2D").build(),
                                aCard().withStringCard("12D").build()
                        ),
                        PlayerTwo
                }
        };
    }
}