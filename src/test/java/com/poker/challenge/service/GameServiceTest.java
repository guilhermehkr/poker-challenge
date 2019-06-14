package com.poker.challenge.service;

import com.google.common.collect.ImmutableMap;
import com.poker.challenge.round.Round;
import com.poker.challenge.round.RoundBuilder;
import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.hand.HandsToRoundTransformer;
import com.poker.challenge.service.printer.GameReportPrinter;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.round.RoundResult.PlayerOne;
import static com.poker.challenge.round.RoundResult.PlayerTwo;
import static com.poker.challenge.round.RoundResult.Tie;
import static java.util.stream.Collectors.toList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class GameServiceTest {

    @InjectMocks
    private GameService testInstance;

    @Mock
    private HandsToRoundTransformer handsToRoundTransformer;

    @Mock
    private PokerService pokerService;

    @Mock
    private GameReportPrinter gameReportPrinter;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private static class HandAndRoundAndResult {
        String twoPlayerHands;
        Round round;
        RoundResult roundResult;

        HandAndRoundAndResult(String twoPlayerHands, Round round, RoundResult roundResult) {
            this.twoPlayerHands = twoPlayerHands;
            this.round = round;
            this.roundResult = roundResult;
        }
    }

    @Test
    public void shouldTransformStringHandsToRoundsAndPlayGameAndPrintResults() {

        List<HandAndRoundAndResult> handsAndRounds = newArrayList(
                new HandAndRoundAndResult("hands1", RoundBuilder.aRound().build(), PlayerOne),
                new HandAndRoundAndResult("hands2", RoundBuilder.aRound().build(), PlayerTwo),
                new HandAndRoundAndResult("hands3", RoundBuilder.aRound().build(), PlayerOne),
                new HandAndRoundAndResult("hands4", RoundBuilder.aRound().build(), PlayerTwo),
                new HandAndRoundAndResult("hands5", RoundBuilder.aRound().build(), PlayerOne),
                new HandAndRoundAndResult("hands6", RoundBuilder.aRound().build(), PlayerTwo),
                new HandAndRoundAndResult("hands7", RoundBuilder.aRound().build(), PlayerOne),
                new HandAndRoundAndResult("hands8", RoundBuilder.aRound().build(), PlayerTwo),
                new HandAndRoundAndResult("hands9", RoundBuilder.aRound().build(), PlayerOne),
                new HandAndRoundAndResult("hands10", RoundBuilder.aRound().build(), Tie)
        );

        handsAndRounds.forEach(handAndRoundAndResult -> {
            given(handsToRoundTransformer.transform(handAndRoundAndResult.twoPlayerHands)).willReturn(handAndRoundAndResult.round);
            given(pokerService.play(handAndRoundAndResult.round)).willReturn(handAndRoundAndResult.roundResult);
        });

        List<String> twoPlayersHands =
                handsAndRounds
                        .stream()
                        .map(handAndRoundAndResult -> handAndRoundAndResult.twoPlayerHands)
                        .collect(toList());

        testInstance.start(twoPlayersHands);

        ImmutableMap<RoundResult, Long> results =
                ImmutableMap.<RoundResult, Long>builder()
                        .put(PlayerOne, 5L)
                        .put(PlayerTwo, 4L)
                        .put(Tie, 1L)
                        .build();

        handsAndRounds.forEach(handAndRoundAndResult -> {
           verify(handsToRoundTransformer).transform(handAndRoundAndResult.twoPlayerHands);
           verify(pokerService).play(handAndRoundAndResult.round);
        });

        verify(gameReportPrinter).printResultReport(results);
    }
}