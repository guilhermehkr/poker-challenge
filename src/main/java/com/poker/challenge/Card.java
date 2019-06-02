package com.poker.challenge;

import static java.lang.Integer.valueOf;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;

public class Card implements Comparable<Card> {

    private String value;
    private String suit;

    Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
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

        int result;
        if (anotherCard == null) {
            result = -1;
        } else if (anotherCard.getValueAsInt() == null && this.getValueAsInt() == null) {
            result = 0;
        } else {
            result = this.getValueAsInt().compareTo(anotherCard.getValueAsInt());
        }
        return result;
    }
}
