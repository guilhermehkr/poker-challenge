package com.poker.challenge.combination;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.round.card.CardBuilder.aCard;

public class StraightTest extends SingleCombinationTest {

    private final static boolean IS_STRAIGHT = true;

    private Straight testInstance;

    @BeforeMethod
    public void setup() {
        testInstance = new Straight();
    }

    @Override
    protected Combination getTestInstance() {
        return testInstance;
    }

    @Override
    protected Rank getTestRank() {
        return Rank.Straight;
    }

    @DataProvider
    public Object[][] cards() {
        return new Object[][]{
                {
                        null,
                        !IS_STRAIGHT
                },
                {
                        newArrayList(),
                        !IS_STRAIGHT
                },
                {
                        newArrayList(
                                aCard().withStringCard("14C").build(),
                                aCard().withStringCard("2D").build(),
                                aCard().withStringCard("7D").build(),
                                aCard().withStringCard("3C").build(),
                                aCard().withStringCard("10C").build()
                        ),
                        !IS_STRAIGHT
                },
                {
                        newArrayList(
                                aCard().withStringCard("3S").build(),
                                aCard().withStringCard("9S").build(),
                                aCard().withStringCard("4H").build(),
                                aCard().withStringCard("4D").build(),
                                aCard().withStringCard("2D").build()
                        ),
                        !IS_STRAIGHT
                },
                {
                        newArrayList(
                                aCard().withStringCard("2S").build(),
                                aCard().withStringCard("3S").build(),
                                aCard().withStringCard("4H").build(),
                                aCard().withStringCard("5D").build(),
                                aCard().withStringCard("6D").build()
                        ),
                        IS_STRAIGHT
                },
                {
                        newArrayList(
                                aCard().withStringCard("8S").build(),
                                aCard().withStringCard("6S").build(),
                                aCard().withStringCard("5H").build(),
                                aCard().withStringCard("4D").build(),
                                aCard().withStringCard("7D").build()
                        ),
                        IS_STRAIGHT
                }
        };
    }
}