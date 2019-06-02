package com.poker.challenge;

import static com.poker.challenge.Card.newCard;

public class CardBuilder {

    private String value;
    private String suit;

    public static CardBuilder aCard() {
        return new CardBuilder();
    }

    public CardBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public CardBuilder withSuit(String suit) {
        this.suit = suit;
        return this;
    }

    public Card build() {
        return newCard(value, suit);
    }
}
