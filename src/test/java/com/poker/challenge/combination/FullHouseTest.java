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

public class FullHouseTest {

    @InjectMocks
    private FullHouse testInstance;

    @Mock
    private ThreeOfAKind threeOfAKind;

    @Mock
    private Pair pair;

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
    public void shouldReturnWhetherItIsFullHouseCombination(Boolean threeOfAKindResult,
                                                            Boolean pairResult,
                                                            Boolean expectedResult) {

        List<Card> cards = newArrayList(aCard().build());

        given(threeOfAKind.checkCombination(cards)).willReturn(threeOfAKindResult);
        given(pair.checkCombination(cards)).willReturn(pairResult);

        boolean result = testInstance.checkCombination(cards);
        assertThat(result, is(expectedResult));

        verify(threeOfAKind).checkCombination(cards);
        verify(pair).checkCombination(cards);
    }

    @Test
    public void shouldReturnFalseAndNotInteractWithPairGivenThareIsNoThreeOfAKidCombination() {

        List<Card> cards = newArrayList(aCard().build());

        given(threeOfAKind.checkCombination(cards)).willReturn(FALSE);

        boolean result = testInstance.checkCombination(cards);
        assertThat(result, is(FALSE));

        verify(threeOfAKind).checkCombination(cards);
        verify(pair, times(0)).checkCombination(cards);
    }
}