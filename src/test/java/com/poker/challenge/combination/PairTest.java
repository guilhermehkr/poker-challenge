package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.round.card.CardBuilder.aCard;
import static org.hamcrest.CoreMatchers.is;

public class PairTest {

    private Pair testInstance;

    @BeforeMethod
    public void setup() {
        testInstance = new Pair();
    }

    @DataProvider
    public Object[][] cards() {
        return new Object[][]{
                {
                        newArrayList(
                                aCard().withStringCard("14C").build(),
                                aCard().withStringCard("2D").build(),
                                aCard().withStringCard("7D").build(),
                                aCard().withStringCard("3C").build(),
                                aCard().withStringCard("TC").build()
                        ),
                        false
                },
                {
                        newArrayList(
                                aCard().withStringCard("3S").build(),
                                aCard().withStringCard("9S").build(),
                                aCard().withStringCard("4H").build(),
                                aCard().withStringCard("4D").build(),
                                aCard().withStringCard("2D").build()
                        ),
                        true
                },
                {
                        newArrayList(
                                aCard().withStringCard("3S").build(),
                                aCard().withStringCard("3D").build(),
                                aCard().withStringCard("3C").build(),
                                aCard().withStringCard("4D").build(),
                                aCard().withStringCard("11D").build()
                        ),
                        false
                }
        };
    }

    @Test(dataProvider = "cards")
    public void shouldCheckWhetherGivenCardsContainPair(List<Card> cards, boolean isPair) {

        boolean result = testInstance.checkCombination(cards);
        MatcherAssert.assertThat(result, is(isPair));
    }
}