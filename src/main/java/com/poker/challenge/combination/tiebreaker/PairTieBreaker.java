package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.card.Card;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.Comparator.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.*;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@Component
public class PairTieBreaker implements TieBreaker {

    @Override
    public RoundResult breakTie(List<Card> playerOneCards, List<Card> playerTwoCards) {

        Map<String, Long> cardValueAndNumberOfOccurrence =
                emptyIfNull(playerOneCards)
                        .stream()
                        .collect(groupingBy(Card::getValue, counting()));

        List<String> sortedCardValues =
                cardValueAndNumberOfOccurrence
                        .entrySet()
                        .stream()
                        .sorted(comparingByValue(reverseOrder()))
                        .map(Map.Entry::getKey)
                        .collect(toList());


        StreamEx.of(sortedCardValues)
                .headTail((pairRepresentative, rest) -> {
                    return null;
                });

        return null;
    }
}
