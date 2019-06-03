package com.poker.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private HandToPlayTransformer handToPlayTransformer;

    private CardTranslator cardTranslator;

    @Autowired
    public GameService(HandToPlayTransformer handToPlayTransformer, CardTranslator cardTranslator) {
        this.handToPlayTransformer = handToPlayTransformer;
        this.cardTranslator = cardTranslator;
    }

    public void start(List<String> hands) {

        hands.stream()
                .map(handToPlayTransformer::transform)
                .map(cardTranslator::translate)
                .forEach(System.out::println)
                //.map(start -> ) // literally start (evaluate who's the winner)
                //.reduce(null) // reduce the results to number of victories of each player
        ;
    }
}
