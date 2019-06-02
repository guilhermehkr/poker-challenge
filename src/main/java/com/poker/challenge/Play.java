package com.poker.challenge;

import java.util.List;

import static java.util.Collections.unmodifiableList;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

public class Play {

    private List<Card> playerOneCards;
    private List<Card> playerTwoCards;
    private boolean isInvalidHand;

    Play(List<Card> playerOneCards, List<Card> playerTwoCards, boolean isInvalidHand) {
        this.playerOneCards = unmodifiableList(playerOneCards);
        this.playerTwoCards = unmodifiableList(playerTwoCards);
        this.isInvalidHand = isInvalidHand;
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

    @Override
    public String toString() {
        return reflectionToString(this);
    }
}
