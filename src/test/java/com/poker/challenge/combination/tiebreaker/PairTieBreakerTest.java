package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.card.Card;
import org.mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.round.RoundResult.PlayerOne;
import static com.poker.challenge.round.RoundResult.PlayerTwo;
import static com.poker.challenge.round.RoundResult.Tie;
import static com.poker.challenge.round.card.CardBuilder.aCard;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class PairTieBreakerTest {

    @InjectMocks
    private PairTieBreaker testInstance;

    @Mock
    private HighCardTieBreaker highCardTieBreaker;

    @BeforeMethod
    public void setup() {
        initMocks(this);
    }

    @DataProvider
    public Object[][] playerCards() {
        return new Object[][]{
                {
                        newArrayList(
                                aCard().withStringCard("10S").build(),
                                aCard().withStringCard("10C").build(),
                                aCard().withStringCard("2D").build(),
                                aCard().withStringCard("6H").build(),
                                aCard().withStringCard("5S").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("10C").build(),
                                aCard().withStringCard("10S").build(),
                                aCard().withStringCard("2D").build(),
                                aCard().withStringCard("6H").build(),
                                aCard().withStringCard("5S").build()
                        ),
                        Tie
                },
                {
                        newArrayList(
                                aCard().withStringCard("10C").build(),
                                aCard().withStringCard("3D").build(),
                                aCard().withStringCard("3H").build(),
                                aCard().withStringCard("6S").build(),
                                aCard().withStringCard("5C").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("10D").build(),
                                aCard().withStringCard("2H").build(),
                                aCard().withStringCard("2S").build(),
                                aCard().withStringCard("6C").build(),
                                aCard().withStringCard("5D").build()
                        ),
                        PlayerOne
                },
                {
                        newArrayList(
                                aCard().withStringCard("10H").build(),
                                aCard().withStringCard("1S").build(),
                                aCard().withStringCard("3C").build(),
                                aCard().withStringCard("5D").build(),
                                aCard().withStringCard("5H").build()
                        ),
                        newArrayList(
                                aCard().withStringCard("10S").build(),
                                aCard().withStringCard("2C").build(),
                                aCard().withStringCard("3D").build(),
                                aCard().withStringCard("6H").build(),
                                aCard().withStringCard("6S").build()
                        ),
                        PlayerTwo
                }
        };
    }

    @Test(dataProvider = "playerCards")
    public void shouldBreakTheTieAndReturnTheWinner(List<Card> playerOneCards, List<Card> playerTwoCards, RoundResult expectedRoundResult) {

        given(highCardTieBreaker.breakTie(anyList(), anyList())).willReturn(Tie);

        RoundResult roundResult = testInstance.breakTie(playerOneCards, playerTwoCards);
        assertThat(roundResult, is(expectedRoundResult));
    }
}