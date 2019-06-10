package com.poker.challenge.round.card;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.poker.challenge.round.hand.HandToCardsTransformer.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Integer.valueOf;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CardTest {

    private final static String ANY_SUIT = "ANY";
    private final static Integer CARD_LESS_THAN_ANOTHER_CARD_PREDICATE = -1;
    private final static Integer CARD_EQUALS_TO_ANOTHER_CARD_PREDICATE = 0;
    private final static Integer CARD_GREATER_THAN_ANOTHER_CARD_PREDICATE = 1;

    @Test
    public void shouldReturnValueAndSuitCorrectly() {

        Card card = new Card(JACK_NUMBER, ANY_SUIT);
        assertThat(card.getValue(), is(JACK_NUMBER));
        assertThat(card.getSuit(), is(ANY_SUIT));
        assertThat(card.getValueAndSuit(), is(JACK_NUMBER + ANY_SUIT));
        assertThat(card.getValueAsInt(), is(valueOf(JACK_NUMBER)));
    }

    @Test
    public void shouldToStringMethodInvokeGetValueAndSuitMethod() {

        Card card = new Card(QUEEN_NUMBER, ANY_SUIT);
        assertThat(card.toString(), is(card.getValueAndSuit()));
    }

    @DataProvider
    public Object[][] cards() {
        return new Object[][]{
                {
                        new Card(ACE_NUMBER, ANY_SUIT),
                        TRUE
                },
                {
                        new Card(TEN_NUMBER, ANY_SUIT),
                        FALSE
                },
                {
                        new Card(null, ANY_SUIT),
                        FALSE
                }
        };
    }

    @Test(dataProvider = "cards")
    public void shouldReturnWhetherTheCardIsAnAce(Card card, boolean expectedResult) {
        assertThat(card.isAnAce(), is(expectedResult));
    }

    @DataProvider
    public Object[][] twoCardsComparison() {
        return new Object[][]{
                {
                        new Card(null, null),
                        null,
                        FALSE,
                        CARD_GREATER_THAN_ANOTHER_CARD_PREDICATE
                },
                {
                        new Card(EMPTY, EMPTY),
                        null,
                        FALSE,
                        CARD_GREATER_THAN_ANOTHER_CARD_PREDICATE
                },
                {
                        new Card(KING_NUMBER, ANY_SUIT),
                        null,
                        FALSE,
                        CARD_GREATER_THAN_ANOTHER_CARD_PREDICATE
                },
                {
                        new Card(KING_NUMBER, ANY_SUIT),
                        new Card(QUEEN_NUMBER, ANY_SUIT),
                        FALSE,
                        CARD_GREATER_THAN_ANOTHER_CARD_PREDICATE
                },
                {
                        new Card(KING_NUMBER, ANY_SUIT),
                        new Card(ACE_NUMBER, ANY_SUIT),
                        FALSE,
                        CARD_LESS_THAN_ANOTHER_CARD_PREDICATE
                },
                {
                        new Card(QUEEN_NUMBER, ANY_SUIT),
                        new Card(QUEEN_NUMBER, EMPTY),
                        FALSE,
                        CARD_EQUALS_TO_ANOTHER_CARD_PREDICATE
                },
                {
                        new Card(null, null),
                        new Card(null, null),
                        TRUE,
                        CARD_EQUALS_TO_ANOTHER_CARD_PREDICATE
                },
                {
                        new Card(KING_NUMBER, null),
                        new Card(KING_NUMBER, null),
                        TRUE,
                        CARD_EQUALS_TO_ANOTHER_CARD_PREDICATE
                },
                {
                        new Card(null, ANY_SUIT),
                        new Card(null, ANY_SUIT),
                        TRUE,
                        CARD_EQUALS_TO_ANOTHER_CARD_PREDICATE
                },
                {
                        new Card(KING_NUMBER, ANY_SUIT),
                        new Card(KING_NUMBER, ANY_SUIT),
                        TRUE,
                        CARD_EQUALS_TO_ANOTHER_CARD_PREDICATE
                }
        };
    }

    @Test(dataProvider = "twoCardsComparison")
    public void shouldCompareTwoCards(Card card, Card anotherCard,
                                      boolean expectedEqualsResult,
                                      Integer expectedCompareToResult) {

        boolean isEqual = card.equals(anotherCard);
        assertThat(isEqual, is(expectedEqualsResult));

        int compareToResult = card.compareTo(anotherCard);
        assertThat(compareToResult, is(expectedCompareToResult));
    }
}