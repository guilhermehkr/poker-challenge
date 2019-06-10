package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

public abstract class NumberOfAKind {

    protected final static int ONE_COMBINATION_OF = 1;

    boolean checkIf(List<Card> cards, int expectedNumberOfCombinations, final Long expectedNumberOfOccurrences) {

        Map<String, Long> cardValueAndNumberOfOccurrence =
                emptyIfNull(cards)
                        .stream()
                        .collect(groupingBy(Card::getValue, counting()));

        long numberOfCombinations =
                cardValueAndNumberOfOccurrence
                        .values()
                        .stream()
                        .filter(expectedNumberOfOccurrences::equals)
                        .count();

        return numberOfCombinations == expectedNumberOfCombinations;
    }
}
