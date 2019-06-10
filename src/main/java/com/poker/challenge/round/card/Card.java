package com.poker.challenge.round.card;

import com.google.common.base.Objects;
import com.poker.challenge.round.hand.HandToCardsTransformer;
import com.sun.org.apache.xpath.internal.operations.Equals;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;

import static com.poker.challenge.round.hand.HandToCardsTransformer.ACE_NUMBER;
import static java.lang.Integer.valueOf;
import static org.apache.commons.lang.StringUtils.isEmpty;
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

    public boolean isAnAce() {
        return Objects.equal(getValue(), ACE_NUMBER);
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
            result = 1;
        } else if (isEmpty(anotherCard.getValue()) && isEmpty(this.getValue())) {
            result = 0;
        } else {
            result = this.getValueAsInt().compareTo(anotherCard.getValueAsInt());
        }
        return result;
    }
}
