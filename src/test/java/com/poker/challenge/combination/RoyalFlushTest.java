package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.poker.challenge.round.card.CardBuilder.aCard;
import static com.poker.challenge.round.hand.HandToCardsTransformer.ACE_NUMBER;
import static com.poker.challenge.round.hand.HandToCardsTransformer.TEN_NUMBER;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Collections.EMPTY_LIST;
import static org.assertj.core.util.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class RoyalFlushTest {

    @InjectMocks
    private RoyalFlush testInstance;

    @Mock
    private StraightFlush straightFlush;

    @BeforeMethod
    public void setup() {
        initMocks(this);
    }

    @DataProvider
    public Object[][] results() {
        return new Object[][]{
                {
                        null,
                        FALSE,
                        FALSE,
                        0
                },
                {
                        EMPTY_LIST,
                        FALSE,
                        FALSE,
                        0
                },
                {
                        newArrayList(
                                aCard()
                                        .withValue(TEN_NUMBER)
                                        .build()
                        ),
                        FALSE,
                        FALSE,
                        0
                },
                {
                        newArrayList(
                                aCard()
                                        .withValue(ACE_NUMBER)
                                        .build()
                        ),
                        FALSE,
                        FALSE,
                        1
                },
                {
                        newArrayList(
                                aCard()
                                        .withValue(ACE_NUMBER)
                                        .build()
                        ),
                        TRUE,
                        TRUE,
                        1
                }
        };
    }

    @Test(dataProvider = "results")
    public void shouldReturnWhetherItIsRoyalFlushCombination(List<Card> cards,
                                                             Boolean expectedStraightFlushCallResult,
                                                             Boolean expectedTestResult,
                                                             int expectedStraightFlushNumberCalls) {

        given(straightFlush.checkCombination(cards)).willReturn(expectedStraightFlushCallResult);

        boolean result = testInstance.checkCombination(cards);
        assertThat(result, is(expectedTestResult));

        verify(straightFlush, times(expectedStraightFlushNumberCalls)).checkCombination(cards);
    }
}