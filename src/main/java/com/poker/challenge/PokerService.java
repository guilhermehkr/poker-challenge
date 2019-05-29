package com.poker.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokerService {

    private HandSplitter handSplitter;

    @Autowired
    public PokerService(HandSplitter handSplitter) {
        this.handSplitter = handSplitter;
    }

    public void play(List<String> hands) {

        hands.stream()
                .map(handSplitter::splitHands) // rename method
                .map(hand -> "") // literally play (evaluate who's the winner)
                .reduce(null); // reduce the results to number of victories of each player
    }
}
