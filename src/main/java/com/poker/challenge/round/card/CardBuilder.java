package com.poker.challenge.round.card;

import static org.apache.commons.lang.StringUtils.length;

public class CardBuilder {

    final static int ONE_DIGIT_VALUE_INDEX_BEGIN = 0;
    final static int ONE_DIGIT_VALUE_INDEX_END = 1;
    final static int ONE_DIGIT_SUIT_INDEX_BEGIN = 1;
    final static int ONE_DIGIT_SUIT_INDEX_END = 2;

    final static int TWO_DIGITS_VALUE_INDEX_BEGIN = 0;
    final static int TWO_DIGITS_CARD_VALUE_INDEX_END = 2;
    final static int TWO_DIGITS_CARD_SUIT_INDEX_BEGIN = 2;
    final static int TWO_DIGITS_SUIT_INDEX_END = 3;

    final static int ONE_DIGIT_AND_SUIT = 2;
    final static int TWO_DIGITS_AND_SUIT = 3;

    private String value;
    private String suit;

    private CardBuilder() {
    }

    public static CardBuilder aCard() {
        return new CardBuilder();
    }

    public CardBuilder withStringCard(String stringCard) {

        if (length(stringCard) == ONE_DIGIT_AND_SUIT) {
            this.value = stringCard.substring(ONE_DIGIT_VALUE_INDEX_BEGIN, ONE_DIGIT_VALUE_INDEX_END);
            this.suit = stringCard.substring(ONE_DIGIT_SUIT_INDEX_BEGIN, ONE_DIGIT_SUIT_INDEX_END);

        } else if (length(stringCard) == TWO_DIGITS_AND_SUIT) {
            this.value = stringCard.substring(TWO_DIGITS_VALUE_INDEX_BEGIN, TWO_DIGITS_CARD_VALUE_INDEX_END);
            this.suit = stringCard.substring(TWO_DIGITS_CARD_SUIT_INDEX_BEGIN, TWO_DIGITS_SUIT_INDEX_END);
        }
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
