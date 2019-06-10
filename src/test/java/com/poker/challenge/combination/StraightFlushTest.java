package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.poker.challenge.round.card.CardBuilder.aCard;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.util.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class StraightFlushTest {

    @InjectMocks
    private StraightFlush testInstance;

    @Mock
    private Straight straight;

    @Mock
    private Flush flush;

    @BeforeMethod
    public void setup() {
        initMocks(this);
    }

    @DataProvider
    public Object[][] results() {
        return new Object[][]{
                {TRUE, TRUE, TRUE},
                {TRUE, FALSE, FALSE},
        };
    }

    @Test(dataProvider = "results")
    public void shouldReturnWhetherItIsStraightFlushCombination(Boolean expectedStraightResult,
                                                                Boolean expectedFlushResult,
                                                                Boolean expectedResult) {

        List<Card> cards = newArrayList(aCard().build());

        given(straight.checkCombination(cards)).willReturn(expectedStraightResult);
        given(flush.checkCombination(cards)).willReturn(expectedFlushResult);

        boolean result = testInstance.checkCombination(cards);
        assertThat(result, is(expectedResult));

        verify(straight).checkCombination(cards);
        verify(flush).checkCombination(cards);
    }

    @Test
    public void shouldReturnFalseAndNotInteractWithFlushGivenThereIsNoStraightCombination() {

        List<Card> cards = newArrayList(aCard().build());

        given(straight.checkCombination(cards)).willReturn(FALSE);

        boolean result = testInstance.checkCombination(cards);
        assertThat(result, is(FALSE));

        verify(straight).checkCombination(cards);
        verify(flush, times(0)).checkCombination(cards);
    }
}