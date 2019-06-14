package com.poker.challenge.service;

import com.poker.challenge.round.Round;
import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.hand.HandsToRoundTransformer;
import com.poker.challenge.service.printer.GameReportPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class GameService {

    private HandsToRoundTransformer handsToRoundTransformer;
    private PokerService pokerService;
    private GameReportPrinter gameReportPrinter;

    @Autowired
    public GameService(HandsToRoundTransformer handsToRoundTransformer,
                       PokerService pokerService,
                       GameReportPrinter gameReportPrinter) {

        this.handsToRoundTransformer = handsToRoundTransformer;
        this.pokerService = pokerService;
        this.gameReportPrinter = gameReportPrinter;
    }

    public void start(List<String> hands) {

        Map<RoundResult, Long> summarisedResults =
                hands.stream()
                        .map(handsToRoundTransformer::transform)
                        .filter(Round::isValidHand)
                        .map(pokerService::play)
                        .collect(groupingBy(identity(), counting()));

        gameReportPrinter.printResultReport(summarisedResults);
    }
}