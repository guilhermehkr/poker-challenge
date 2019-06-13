package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import com.poker.challenge.round.card.CardBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.combination.RankProvider.NO_COMBINATIONS_ERROR_MESSAGE;
import static com.poker.challenge.combination.RankProvider.NO_DEFAULT_COMBINATION_ERROR_MESSAGE;
import static com.poker.challenge.combination.RankProvider.NO_RANK_MAPPING_COMBINATION_ERROR_MESSAGE;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.fail;

public class RankProviderTest {

    private RankProvider testInstance;

    private Combination combinationMock;

    @BeforeMethod
    public void setup() {
        testInstance = new RankProvider();
        this.setupTestData();
    }

    private void setupTestData() {

        combinationMock = new Combination() {

            @Override
            public int getOrder() {
                return 0;
            }

            @Override
            public boolean checkCombination(List<Card> cards) {
                return isNotEmpty(cards);
            }

            @Override
            public Rank getRank() {
                return Rank.RoyalFlush;
            }
        };

        List<Combination> combinations =
                Arrays.stream(Rank.values())
                        .map(rank -> combinationMock)
                        .collect(toList());

        testInstance.setCombinations(combinations);
    }

    @Test
    public void shouldInvokeCombinationMockAndReturnRoyalFlushRank() {
        List<Card> cards = newArrayList(CardBuilder.aCard().build());

        Rank bestRank = testInstance.findBestRank(cards);
        assertThat(bestRank, is(Rank.RoyalFlush));
    }

    @Test
    public void shouldThrownException() {
        try {
            testInstance.findBestRank(newArrayList());
            fail("Should've thrown an RuntimeException given no combination matched");
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), is(NO_DEFAULT_COMBINATION_ERROR_MESSAGE));
        }
    }

    @Test
    public void shouldThrowExceptionGivenNoCombinationsWereInjected() {

        try {
            testInstance.setCombinations(newArrayList());
            fail("Should've thrown an RuntimeException given no combination were injected");
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), is(NO_COMBINATIONS_ERROR_MESSAGE));
        }
    }

    @Test
    public void shouldThrowExceptionGivenNoAllCombinationsWereMappedInRankEnumeration() {

        try {
            testInstance.setCombinations(newArrayList(combinationMock));
            fail("Should've thrown an RuntimeException given no all combination were injected");
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), is(NO_RANK_MAPPING_COMBINATION_ERROR_MESSAGE));
        }
    }
}