package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.combination.Rank;
import com.poker.challenge.round.RoundResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.util.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoyalFlushTieBreakerTest extends BaseTieBreakerTest {

    private RoyalFlushTieBreaker testInstance;

    @BeforeMethod
    private void setup() {
        testInstance = new RoyalFlushTieBreaker();
    }

    @Override
    protected TieBreaker getTestInstance() {
        return testInstance;
    }

    @Override
    protected Rank getRelatedRank() {
        return Rank.RoyalFlush;
    }

    @Test
    public void shouldAlwaysReturnTieGivenRoyalFlushIsUnbeatable() {
        RoundResult roundResult = testInstance.breakTie(newArrayList(), newArrayList());
        assertThat(roundResult, is(RoundResult.Tie));
    }
}