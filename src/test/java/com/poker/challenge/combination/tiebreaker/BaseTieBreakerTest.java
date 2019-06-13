package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.combination.Rank;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public abstract class BaseTieBreakerTest {

    protected abstract TieBreaker getTestInstance();
    protected abstract Rank getRelatedRank();

    @Test
    public void shouldContainGivenRank() {
        boolean valid =
                getTestInstance().correspondingRanks() != null
                        && getTestInstance().correspondingRanks().contains(getRelatedRank());

        assertThat("testInstance should be related to given rank: " + getRelatedRank(), valid);
    }
}