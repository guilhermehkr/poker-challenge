package com.poker.challenge;

import static java.lang.Integer.valueOf;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;

public class Card implements Comparable<Card> {

    public final static int VALUE_INDEX_BEGIN = 0;
    public final static int VALUE_INDEX_END = 1;
    public final static int SUIT_INDEX_BEGIN = 1;
    public final static int SUIT_INDEX_END = 2;

    private String value;
    private String suit;

    private Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    static Card newCard(String stringCard) {
        String value = stringCard.substring(VALUE_INDEX_BEGIN, VALUE_INDEX_END);
        String suit = stringCard.substring(SUIT_INDEX_BEGIN, SUIT_INDEX_END);
        return new Card(value, suit);
    }

    static Card newCard(String value, String suit) {
        return new Card(value, suit);
    }

    public String getValue() {
        return value;
    }

    public Integer getValueAsInt() {
        return valueOf(value);
    }

    public String getSuit() {
        return suit;
    }

    public String getValueAndSuit() {
        return this.value + this.suit;
    }

    @Override
    public boolean equals(Object anotherCard) {
        return anotherCard instanceof Card
                && reflectionEquals(this, anotherCard);
    }

    @Override
    public String toString() {
        return getValueAndSuit();
    }

    @Override
    public int compareTo(Card anotherCard) {
        return this.getValueAsInt().compareTo(anotherCard.getValueAsInt());
    }
}
