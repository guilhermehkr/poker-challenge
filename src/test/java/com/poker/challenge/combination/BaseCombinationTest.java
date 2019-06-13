package com.poker.challenge.combination;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class BaseCombinationTest {

    protected abstract Combination getTestInstance();
    protected abstract Rank getTestRank();

    @Test
    public void shouldReturnCorrespondingRank() {
        assertThat(getTestInstance().getRank(), is(getTestRank()));
    }
}
