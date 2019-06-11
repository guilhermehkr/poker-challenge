package com.poker.challenge.round;

import java.util.function.Supplier;

public enum RoundResult {

    PlayerOne,
    PlayerTwo,
    Tie;

    public static RoundResult decideWhoTheWinnerIs(Integer playerOneCard,
                                                   Integer playerTwoCard,
                                                   Supplier<RoundResult> tieBreakerFunction) {
        RoundResult roundResult;
        if (playerOneCard > playerTwoCard) {
            roundResult = PlayerOne;
        } else if (playerTwoCard > playerOneCard) {
            roundResult = PlayerTwo;
        } else {
            roundResult = tieBreakerFunction.get();
        }
        return roundResult;
    }
}
