package com.poker.challenge.combination;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.round.card.CardBuilder.aCard;

public class FlushTest extends SingleCombinationTest {

    private final static boolean IS_FLUSH = true;

    private Flush testInstance;

    @BeforeMethod
    public void setup() {
        testInstance = new Flush();
    }

    @Override
    protected Combination getTestInstance() {
        return testInstance;
    }

    @Override
    protected Rank getTestRank() {
        return Rank.Flush;
    }

    @DataProvider
    public Object[][] cards() {
        return new Object[][]{
                {
                        null,
                        !IS_FLUSH
                },
                {
                        newArrayList(),
                        !IS_FLUSH
                },
                {
                        newArrayList(
                                aCard().withStringCard("14S").build(),
                                aCard().withStringCard("2H").build(),
                                aCard().withStringCard("7D").build(),
                                aCard().withStringCard("3C").build(),
                                aCard().withStringCard("10C").build()
                        ),
                        !IS_FLUSH
                },
                {
                        newArrayList(
                                aCard().withStringCard("14C").build(),
                                aCard().withStringCard("2C").build(),
                                aCard().withStringCard("7S").build(),
                                aCard().withStringCard("3C").build(),
                                aCard().withStringCard("10S").build()
                        ),
                        !IS_FLUSH
                },
                {
                        newArrayList(
                                aCard().withStringCard("3S").build(),
                                aCard().withStringCard("9S").build(),
                                aCard().withStringCard("4S").build(),
                                aCard().withStringCard("4S").build(),
                                aCard().withStringCard("2S").build()
                        ),
                        IS_FLUSH
                },
                {
                        newArrayList(
                                aCard().withStringCard("3D").build(),
                                aCard().withStringCard("9D").build(),
                                aCard().withStringCard("4D").build(),
                                aCard().withStringCard("4D").build(),
                                aCard().withStringCard("2D").build()
                        ),
                        IS_FLUSH
                }
        };
    }
}