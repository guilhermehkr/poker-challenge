package com.poker.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokerService {

    private HandToPlayTransformer handToPlayTransformer;

    private CardTranslator cardTranslator;

    @Autowired
    public PokerService(HandToPlayTransformer handToPlayTransformer, CardTranslator cardTranslator) {
        this.handToPlayTransformer = handToPlayTransformer;
        this.cardTranslator = cardTranslator;
    }

    public void play(List<String> hands) {

        hands.stream()
                .map(handToPlayTransformer::transform)
                .map(cardTranslator::translate)
                .forEach(System.out::println)
                //.map(play -> ) // literally play (evaluate who's the winner)
                //.reduce(null) // reduce the results to number of victories of each player
        ;
    }
}
