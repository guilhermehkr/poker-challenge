package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.combination.Rank;
import org.testng.annotations.DataProvider;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.combination.Rank.Straight;
import static com.poker.challenge.combination.Rank.TwoPairs;
import static com.poker.challenge.round.RoundResult.*;
import static com.poker.challenge.round.card.CardBuilder.aCard;

public class TwoPairsTieBreakerTest extends PairTieBreakerTest {

    @Override
    protected Rank getRelatedRank() {
        return TwoPairs;
    }

    @DataProvider
    public Object[][] playerCards() {
        return new Object[][]{
                {
                        newArrayList(
                                aCard().withStringCard("6S").build(),
                                aCard().withStringCard("10C").build(),
                                aCard().withStringCard("6D").build(),
                                aCard().withStringCard("10H").build(),
                                aCard().withStringCard("5S").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("6D").build(),
                                aCard().withStringCard("6S").build(),
                                aCard().withStringCard("10D").build(),
                                aCard().withStringCard("10S").build(),
                                aCard().withStringCard("5C").build()
                        ),
                        Tie
                },
                {
                        newArrayList(
                                aCard().withStringCard("7S").build(),
                                aCard().withStringCard("5C").build(),
                                aCard().withStringCard("7D").build(),
                                aCard().withStringCard("5H").build(),
                                aCard().withStringCard("6S").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("7C").build(),
                                aCard().withStringCard("5D").build(),
                                aCard().withStringCard("6H").build(),
                                aCard().withStringCard("6S").build(),
                                aCard().withStringCard("7C").build()
                        ),
                        PlayerTwo
                },
                {
                        newArrayList(
                                aCard().withStringCard("14S").build(),
                                aCard().withStringCard("12C").build(),
                                aCard().withStringCard("14D").build(),
                                aCard().withStringCard("12H").build(),
                                aCard().withStringCard("11S").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("10C").build(),
                                aCard().withStringCard("9D").build(),
                                aCard().withStringCard("9H").build(),
                                aCard().withStringCard("10S").build(),
                                aCard().withStringCard("6C").build()
                        ),
                        PlayerOne
                }
        };
    }
}