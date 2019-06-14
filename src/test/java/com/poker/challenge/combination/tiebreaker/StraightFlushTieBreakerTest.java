package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.combination.Rank;
import org.testng.annotations.DataProvider;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.combination.Rank.Straight;
import static com.poker.challenge.combination.Rank.StraightFlush;
import static com.poker.challenge.round.RoundResult.*;
import static com.poker.challenge.round.card.CardBuilder.aCard;

public class StraightFlushTieBreakerTest extends HighCardTieBreakerTest {

    @Override
    protected Rank getRelatedRank() {
        return StraightFlush;
    }

    @DataProvider
    public Object[][] playerCards() {
        return new Object[][]{
                {
                        newArrayList(
                                aCard().withStringCard("10S").build(),
                                aCard().withStringCard("8S").build(),
                                aCard().withStringCard("6S").build(),
                                aCard().withStringCard("7S").build(),
                                aCard().withStringCard("5S").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("10D").build(),
                                aCard().withStringCard("8D").build(),
                                aCard().withStringCard("6D").build(),
                                aCard().withStringCard("7D").build(),
                                aCard().withStringCard("5D").build()
                        ),
                        Tie
                },
                {
                        newArrayList(
                                aCard().withStringCard("7C").build(),
                                aCard().withStringCard("5C").build(),
                                aCard().withStringCard("4C").build(),
                                aCard().withStringCard("3C").build(),
                                aCard().withStringCard("6C").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("7H").build(),
                                aCard().withStringCard("5H").build(),
                                aCard().withStringCard("6H").build(),
                                aCard().withStringCard("4H").build(),
                                aCard().withStringCard("8H").build()
                        ),
                        PlayerTwo
                },
                {
                        newArrayList(
                                aCard().withStringCard("14D").build(),
                                aCard().withStringCard("12D").build(),
                                aCard().withStringCard("10D").build(),
                                aCard().withStringCard("13D").build(),
                                aCard().withStringCard("11D").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("10D").build(),
                                aCard().withStringCard("9D").build(),
                                aCard().withStringCard("8D").build(),
                                aCard().withStringCard("7D").build(),
                                aCard().withStringCard("6D").build()
                        ),
                        PlayerOne
                }
        };
    }
}