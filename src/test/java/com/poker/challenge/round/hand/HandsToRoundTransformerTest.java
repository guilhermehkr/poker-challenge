package com.poker.challenge.round.hand;

import com.poker.challenge.round.Round;
import com.poker.challenge.round.RoundValidator;
import com.poker.challenge.round.card.Card;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.poker.challenge.infrastructure.FileReaderTest.*;
import static com.poker.challenge.round.card.CardBuilder.aCard;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.util.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class HandsToRoundTransformerTest {

    private final static int FIRST_POSITION = 0;

    @InjectMocks
    private HandsToRoundTransformer testInstance;

    @Mock
    private HandToCardsTransformer handToCardsTransformer;

    @Mock
    private RoundValidator roundValidator;

    @BeforeMethod
    public void setup() {
        initMocks(this);
    }

    @DataProvider
    public Object[][] hands() {
        return new Object[][]{
                {
                        FIRST_HAND,
                        FIRST_HAND.substring(FIRST_POSITION, getMiddlePositionOf(FIRST_HAND)),
                        FIRST_HAND.substring(getMiddlePositionOf(FIRST_HAND) + 1)
                },
                {
                        SECOND_HAND,
                        SECOND_HAND.substring(FIRST_POSITION, getMiddlePositionOf(SECOND_HAND)),
                        SECOND_HAND.substring(getMiddlePositionOf(SECOND_HAND) + 1)
                }
        };
    }

    private int getMiddlePositionOf(String string) {
        return string.length() / 2;
    }

    @Test(dataProvider = "hands")
    public void shouldReturnRoundPopulatedWithCardsGivenValidHands(String hands,
                                                                   String playerOneHand,
                                                                   String playerTwoHand) {

        List<Card> playOneCards = newArrayList(aCard().build());
        List<Card> playTwoCards = newArrayList(aCard().build());

        given(roundValidator.isAnInvalidRound(hands)).willReturn(FALSE);
        given(handToCardsTransformer.transform(playerOneHand)).willReturn(playOneCards);
        given(handToCardsTransformer.transform(playerTwoHand)).willReturn(playTwoCards);

        Round round = testInstance.transform(hands);

        assertThat(round.getPlayerOneCards(), contains(playOneCards.toArray()));
        assertThat(round.getPlayerTwoCards(), contains(playTwoCards.toArray()));
        assertThat(round.isInvalidHand(), is(FALSE));

        verify(roundValidator).isAnInvalidRound(hands);
        verify(handToCardsTransformer).transform(playerOneHand);
        verify(handToCardsTransformer).transform(playerTwoHand);
    }

    @DataProvider
    public Object[][] invalidHands() {
        return new Object[][]{
                {
                        null,
                },
                {
                        FIRST_HAND_WHITESPACE
                }
        };
    }

    @Test(dataProvider = "invalidHands")
    public void shouldReturnInvalidRoundWithNoCardsGivenInvalidHands(String invalidHand) {

        given(roundValidator.isAnInvalidRound(invalidHand)).willReturn(TRUE);
        Round round = testInstance.transform(invalidHand);
        assertThat(round.isInvalidHand(), is(TRUE));
    }
}