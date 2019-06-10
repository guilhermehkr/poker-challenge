package com.poker.challenge.combination;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.round.card.CardBuilder.aCard;

public class FourOfAKindTest extends SingleCombinationTest {

    private final static boolean CONTAINS_FOUR_OF_A_KIND = true;

    private FourOfAKind testInstance;

    @BeforeMethod
    public void setup() {
        testInstance = new FourOfAKind();
    }

    @Override
    protected Combination getTestInstance() {
        return testInstance;
    }

    @Override
    protected Rank getTestRank() {
        return Rank.FourOfAKind;
    }

    @DataProvider
    public Object[][] cards() {
        return new Object[][]{
                {
                        null,
                        !CONTAINS_FOUR_OF_A_KIND
                },
                {
                        newArrayList(),
                        !CONTAINS_FOUR_OF_A_KIND
                },
                {
                        newArrayList(
                                aCard().withStringCard("14C").build(),
                                aCard().withStringCard("2D").build(),
                                aCard().withStringCard("7D").build(),
                                aCard().withStringCard("3C").build(),
                                aCard().withStringCard("10C").build()
                        ),
                        !CONTAINS_FOUR_OF_A_KIND
                },
                {
                        newArrayList(
                                aCard().withStringCard("3S").build(),
                                aCard().withStringCard("9S").build(),
                                aCard().withStringCard("4H").build(),
                                aCard().withStringCard("4D").build(),
                                aCard().withStringCard("2D").build()
                        ),
                        !CONTAINS_FOUR_OF_A_KIND
                },
                {
                        newArrayList(
                                aCard().withStringCard("3S").build(),
                                aCard().withStringCard("9S").build(),
                                aCard().withStringCard("12H").build(),
                                aCard().withStringCard("12D").build(),
                                aCard().withStringCard("2D").build()
                        ),
                        !CONTAINS_FOUR_OF_A_KIND
                },
                {
                        newArrayList(
                                aCard().withStringCard("3S").build(),
                                aCard().withStringCard("3D").build(),
                                aCard().withStringCard("3C").build(),
                                aCard().withStringCard("4D").build(),
                                aCard().withStringCard("11D").build()
                        ),
                        !CONTAINS_FOUR_OF_A_KIND
                },
                {
                        newArrayList(
                                aCard().withStringCard("14S").build(),
                                aCard().withStringCard("14D").build(),
                                aCard().withStringCard("14C").build(),
                                aCard().withStringCard("14D").build(),
                                aCard().withStringCard("11D").build()
                        ),
                        CONTAINS_FOUR_OF_A_KIND
                },
                {
                        newArrayList(
                                aCard().withStringCard("3S").build(),
                                aCard().withStringCard("3H").build(),
                                aCard().withStringCard("3C").build(),
                                aCard().withStringCard("3D").build(),
                                aCard().withStringCard("11D").build()
                        ),
                        CONTAINS_FOUR_OF_A_KIND
                },
                {
                        newArrayList(
                                aCard().withStringCard("3S").build(),
                                aCard().withStringCard("3D").build(),
                                aCard().withStringCard("4C").build(),
                                aCard().withStringCard("4D").build(),
                                aCard().withStringCard("11D").build()
                        ),
                        !CONTAINS_FOUR_OF_A_KIND
                },
                {
                        newArrayList(
                                aCard().withStringCard("14S").build(),
                                aCard().withStringCard("14S").build(),
                                aCard().withStringCard("6H").build(),
                                aCard().withStringCard("6D").build(),
                                aCard().withStringCard("6D").build()
                        ),
                        !CONTAINS_FOUR_OF_A_KIND
                },
                {
                        newArrayList(
                                aCard().withStringCard("2S").build(),
                                aCard().withStringCard("3S").build(),
                                aCard().withStringCard("4H").build(),
                                aCard().withStringCard("5D").build(),
                                aCard().withStringCard("6D").build()
                        ),
                        !CONTAINS_FOUR_OF_A_KIND
                }
        };
    }
}