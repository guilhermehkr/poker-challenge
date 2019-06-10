package com.poker.challenge.service;

import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.hand.HandsToRoundTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class GameService {

    private HandsToRoundTransformer handsToRoundTransformer;
    private PokerService pokerService;

    @Autowired
    public GameService(HandsToRoundTransformer handsToRoundTransformer, PokerService pokerService) {
        this.handsToRoundTransformer = handsToRoundTransformer;
        this.pokerService = pokerService;
    }

    public void start(List<String> hands) {

        Map<RoundResult, Long> report =
                hands.stream()
                        .map(handsToRoundTransformer::transform)
                        .map(pokerService::play)
                        .collect(groupingBy(identity(), counting()));

        System.out.println("Player 1: " + report.get(RoundResult.PlayerOne));
        System.out.println("Player 2: " + report.get(RoundResult.PlayerTwo));
        System.out.println("Tied: " + report.get(RoundResult.Tie));
    }
}