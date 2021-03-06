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

public class StraightTieBreakerTest extends HighCardTieBreakerTest {

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
                                aCard().withStringCard("8C").build(),
                                aCard().withStringCard("6D").build(),
                                aCard().withStringCard("7H").build(),
                                aCard().withStringCard("5S").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("10D").build(),
                                aCard().withStringCard("8S").build(),
                                aCard().withStringCard("6D").build(),
                                aCard().withStringCard("7S").build(),
                                aCard().withStringCard("5C").build()
                        ),
                        Tie
                },
                {
                        newArrayList(
                                aCard().withStringCard("7S").build(),
                                aCard().withStringCard("5C").build(),
                                aCard().withStringCard("4D").build(),
                                aCard().withStringCard("3H").build(),
                                aCard().withStringCard("6S").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("7C").build(),
                                aCard().withStringCard("5D").build(),
                                aCard().withStringCard("6H").build(),
                                aCard().withStringCard("4S").build(),
                                aCard().withStringCard("8C").build()
                        ),
                        PlayerTwo
                },
                {
                        newArrayList(
                                aCard().withStringCard("14S").build(),
                                aCard().withStringCard("12C").build(),
                                aCard().withStringCard("10D").build(),
                                aCard().withStringCard("13H").build(),
                                aCard().withStringCard("11S").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("10C").build(),
                                aCard().withStringCard("9D").build(),
                                aCard().withStringCard("8H").build(),
                                aCard().withStringCard("7S").build(),
                                aCard().withStringCard("6C").build()
                        ),
                        PlayerOne
                }
        };
    }
}