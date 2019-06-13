package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class SingleCombinationTest extends BaseCombinationTest {

    protected abstract Combination getTestInstance();

    @Test(dataProvider = "cards")
    public void shouldCheckWhetherGivenCardsContainDesiredCombination(List<Card> cards,
                                                                      boolean isExpectedCombination) {

        boolean result = getTestInstance().checkCombination(cards);
        assertThat(result, is(isExpectedCombination));
    }
}
