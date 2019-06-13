package com.poker.challenge.service;

import com.poker.challenge.combination.Rank;
import com.poker.challenge.combination.RankProvider;
import com.poker.challenge.combination.tiebreaker.HighCardTieBreaker;
import com.poker.challenge.combination.tiebreaker.TieBreakerFactory;
import com.poker.challenge.round.Round;
import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.card.Card;
import com.poker.challenge.round.card.CardBuilder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.round.RoundBuilder.aRound;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class PokerServiceTest {

    @InjectMocks
    private PokerService testInstance;

    @Mock
    private RankProvider rankProvider;

    @Mock
    private TieBreakerFactory tieBreakerFactory;

    @Mock
    private HighCardTieBreaker defaultTieBreaker;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @DataProvider
    public Object[][] rounds() {
        return new Object[][]{
                {
                        newArrayList(CardBuilder.aCard().withValue("Player1").build()),
                        Rank.Flush,
                        newArrayList(CardBuilder.aCard().withValue("Player2").build()),
                        Rank.FullHouse,
                        RoundResult.PlayerTwo

                },
                {
                        newArrayList(CardBuilder.aCard().withValue("Player1").build()),
                        Rank.StraightFlush,
                        newArrayList(CardBuilder.aCard().withValue("Player2").build()),
                        Rank.FourOfAKind,
                        RoundResult.PlayerOne
                },
                {
                        newArrayList(CardBuilder.aCard().withValue("Player1").build()),
                        Rank.RoyalFlush,
                        newArrayList(CardBuilder.aCard().withValue("Player2").build()),
                        Rank.RoyalFlush,
                        RoundResult.Tie
                }
        };
    }

    @Test(dataProvider = "rounds")
    public void shouldReturnCorrectWinnerGivenPlayersCards(List<Card> playerOneCards,
                                                           Rank playerOneRank,
                                                           List<Card> playerTwoCards,
                                                           Rank playerTwoRank,
                                                           RoundResult expectedRoundResult) {

        Round round = aRound()
                .withPlayerOneCards(playerOneCards)
                .withPlayerTwoCards(playerTwoCards)
                .build();

        given(tieBreakerFactory.newTieBreaker(playerOneRank)).willReturn(defaultTieBreaker);
        given(defaultTieBreaker.breakTie(playerOneCards, playerTwoCards)).willReturn(RoundResult.Tie);

        given(rankProvider.findBestRank(playerOneCards)).willReturn(playerOneRank);
        given(rankProvider.findBestRank(playerTwoCards)).willReturn(playerTwoRank);

        RoundResult roundResult = testInstance.play(round);

        assertThat(roundResult, is(expectedRoundResult));

        verify(rankProvider).findBestRank(playerOneCards);
        verify(rankProvider).findBestRank(playerTwoCards);

        if (RoundResult.Tie == expectedRoundResult) {
            verify(tieBreakerFactory).newTieBreaker(playerOneRank);
            verify(defaultTieBreaker).breakTie(playerOneCards, playerTwoCards);
        }

    }
}