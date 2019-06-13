package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.round.card.Card;

import java.util.List;
import java.util.Map;

import static com.poker.challenge.util.ListUtil.head;
import static java.util.Comparator.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

abstract class BaseTieBreaker {

    final static Integer DEFAULT_CARD_VALUE = 0;

    Integer getFirstPlayersCard(List<Card> playersCards) {
        return head(playersCards)
                .map(Card::getValueAsInt)
                .orElse(DEFAULT_CARD_VALUE);
    }

    List<Integer> collectPlayersCardInOrderOfImportance(List<Card> playersCards) {

        Map<Integer, Long> cardsAndNumberOfOccurrences =
                emptyIfNull(playersCards)
                        .stream()
                        .collect(groupingBy(Card::getValueAsInt, counting()));

        return cardsAndNumberOfOccurrences
                .entrySet()
                .stream()
                .sorted(comparingByValue(reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(toList());
    }
}
