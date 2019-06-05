package com.poker.challenge.round;

import com.poker.challenge.round.card.Card;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class RoundBuilder {

    private List<Card> playerOneCards = newArrayList();
    private List<Card> playerTwoCards = newArrayList();
    private boolean isInvalidHand;

    private RoundBuilder() {
    }

    public static RoundBuilder aRound() {
        return new RoundBuilder();
    }

    public RoundBuilder withPlayerOneCards(List<Card> playerOneCards) {
        this.playerOneCards = playerOneCards;
        return this;
    }

    public RoundBuilder withPlayerTwoCards(List<Card> playerTwoCards) {
        this.playerTwoCards = playerTwoCards;
        return this;
    }

    public RoundBuilder withIsInvalidHand(boolean isInvalidHand) {
        this.isInvalidHand = isInvalidHand;
        return this;
    }

    public Round build() {
        return new Round(playerOneCards, playerTwoCards, isInvalidHand);
    }
}
