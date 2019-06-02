package com.poker.challenge;

public class CardBuilder {

    public final static int VALUE_INDEX_BEGIN = 0;
    public final static int VALUE_INDEX_END = 1;
    public final static int SUIT_INDEX_BEGIN = 1;
    public final static int SUIT_INDEX_END = 2;

    private String value;
    private String suit;

    private CardBuilder() {
    }

    public static CardBuilder aCard() {
        return new CardBuilder();
    }

    public CardBuilder withStringCard(String stringCard) {
        this.value = stringCard.substring(VALUE_INDEX_BEGIN, VALUE_INDEX_END);
        this.suit = stringCard.substring(SUIT_INDEX_BEGIN, SUIT_INDEX_END);
        return this;
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
        return new Card(value, suit);
    }
}
