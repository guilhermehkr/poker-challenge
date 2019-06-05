package com.poker.challenge.service;

import com.poker.challenge.round.hand.HandsToRoundTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        hands.stream()
                .map(handsToRoundTransformer::transform)
                .map(pokerService::play)
                //.map(start -> ) // literally start (evaluate who's the winner)
                //.reduce(null) // reduce the results to number of victories of each player
        ;
    }
}