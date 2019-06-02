package com.poker.challenge;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Play {

    private List<Card> playerOneCards;
    private List<Card> playerTwoCards;
    private boolean isInvalidHand;

    public Play(List<Card> playerOneCards, List<Card> playerTwoCards) {
        this.playerOneCards = unmodifiableList(playerOneCards);
        this.playerTwoCards = unmodifiableList(playerTwoCards);
    }

    public Play(boolean isInvalid) {
        this.isInvalidHand = isInvalid;
    }

    public List<Card> getPlayerOneCards() {
        return playerOneCards;
    }

    public List<Card> getPlayerTwoCards() {
        return playerTwoCards;
    }

    public boolean isInvalidHand() {
        return isInvalidHand;
    }

    public boolean isValidHand() {
        return !isInvalidHand;
    }
}
