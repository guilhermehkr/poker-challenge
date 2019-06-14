package com.poker.challenge.combination.tiebreaker;

import com.google.common.collect.Lists;
import com.poker.challenge.combination.Rank;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TieBreakerFactoryTest {

    private TieBreakerFactory testInstance;

    @BeforeMethod
    public void setup() {
        testInstance = new TieBreakerFactory();
        List<TieBreaker> tieBreakers =
                Lists.newArrayList(
                        new HighCardTieBreaker(),
                        new PairTieBreaker(new HighCardTieBreaker()),
                        new RoyalFlushTieBreaker()
                );
        testInstance.setTieBreakers(tieBreakers);
    }

    @DataProvider
    public Object[][] factory() {
        return new Object[][]{
                {Rank.HighCard, HighCardTieBreaker.class},
                {Rank.Pair, PairTieBreaker.class},
                {Rank.TwoPairs, PairTieBreaker.class},
                {Rank.ThreeOfAKind, PairTieBreaker.class},
                {Rank.Straight, HighCardTieBreaker.class},
                {Rank.Flush, HighCardTieBreaker.class},
                {Rank.FullHouse, PairTieBreaker.class},
                {Rank.FourOfAKind, PairTieBreaker.class},
                {Rank.StraightFlush, HighCardTieBreaker.class},
                {Rank.RoyalFlush, RoyalFlushTieBreaker.class},
        };
    }

    @Test(dataProvider = "factory")
    public void shouldReturnCorrespondingTieBreakerGivenRank(Rank rank, Class<TieBreaker> implementation) {
        TieBreaker tieBreaker = testInstance.newTieBreaker(rank);
        assertTrue("It got wrong implementation", implementation.equals(tieBreaker.getClass()));
        assertThat(tieBreaker.correspondingRanks(), hasItem(rank));
    }
}