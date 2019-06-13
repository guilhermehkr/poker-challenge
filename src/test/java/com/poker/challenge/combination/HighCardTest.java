package com.poker.challenge.combination;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HighCardTest extends BaseCombinationTest {

    private HighCard testInstance;

    @BeforeMethod
    public void setup() {
        testInstance = new HighCard();
    }

    @Override
    protected Combination getTestInstance() {
        return testInstance;
    }

    @Override
    protected Rank getTestRank() {
        return Rank.HighCard;
    }

    @Test
    public void shouldReturnAlwaysTrueGivenHighCardIsDefaultCombination() {
        boolean result = testInstance.checkCombination(null);
        assertThat(result, is(TRUE));
    }
}