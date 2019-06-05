package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

public abstract class NumberOfAKind {

    protected final static int NUMBER_OF_COMBINATIONS = 1;

    protected boolean checkIf(List<Card> cards, int number, int numberOfOccur) {
        Map<String, Long> cardValueAndOccurrence =
                emptyIfNull(cards)
                        .stream()
                        .collect(groupingBy(Card::getValue, counting()));

        long occurrences =
                cardValueAndOccurrence
                        .values()
                        .stream()
                        .filter(count -> count == number)
                        .count();

        return occurrences == numberOfOccur;
    }
}
