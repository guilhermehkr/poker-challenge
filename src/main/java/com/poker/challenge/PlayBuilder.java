package com.poker.challenge;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class PlayBuilder {

    private List<Card> playerOneCards = newArrayList();
    private List<Card> playerTwoCards = newArrayList();
    private boolean isInvalidHand;

    private PlayBuilder() {
    }

    public static PlayBuilder aPlay() {
        return new PlayBuilder();
    }

    public PlayBuilder withPlayerOneCards(List<Card> playerOneCards) {
        this.playerOneCards = playerOneCards;
        return this;
    }

    public PlayBuilder withPlayerTwoCards(List<Card> playerTwoCards) {
        this.playerTwoCards = playerTwoCards;
        return this;
    }

    public PlayBuilder withIsInvalidHand(boolean isInvalidHand) {
        this.isInvalidHand = isInvalidHand;
        return this;
    }

    public Play build() {
        return new Play(playerOneCards, playerTwoCards, isInvalidHand);
    }
}
