package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.combination.Rank;
import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.card.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static com.poker.challenge.combination.Rank.*;
import static com.poker.challenge.round.card.CardBuilder.aCard;
import static com.poker.challenge.util.ListUtil.head;
import static com.poker.challenge.util.ListUtil.tail;
import static java.util.Comparator.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.*;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@Component
public class PairTieBreaker implements TieBreaker {

    private HighCardTieBreaker highCardTieBreaker;

    @Autowired
    public PairTieBreaker(HighCardTieBreaker highCardTieBreaker) {
        this.highCardTieBreaker = highCardTieBreaker;
    }

    @Override
    public RoundResult breakTie(List<Card> playerOneCards, List<Card> playerTwoCards) {

        final List<Integer> sortedPlayerOneCards = collectPlayersCardInOrderOfOccurrence(playerOneCards);
        final List<Integer> sortedPlayerTwoCards = collectPlayersCardInOrderOfOccurrence(playerTwoCards);

        Integer playerOnePair = getFirstPlayersCardValue(sortedPlayerOneCards);
        Integer playerTwoPair = getFirstPlayersCardValue(sortedPlayerTwoCards);

        return RoundResult.decideWhoTheWinnerIs(
                playerOnePair,
                playerTwoPair,
                () -> highCardTieBreaker.breakTie(
                        tail(mapValueToCard(sortedPlayerOneCards)),
                        tail(mapValueToCard(sortedPlayerTwoCards))
                )
        );
    }

    //#IMPROVEMENT-2
    @Override
    public Set<Rank> correspondingRanks() {
        return newHashSet(
                Pair,
                TwoPairs,
                ThreeOfAKind,
                FullHouse,
                FourOfAKind
        );
    }

    private List<Integer> collectPlayersCardInOrderOfOccurrence(List<Card> playersCards) {

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

    private Integer getFirstPlayersCardValue(List<Integer> sortedPlayeCards) {
        return head(sortedPlayeCards)
                .orElse(DEFAULT_CARD_VALUE);
    }

    private List<Card> mapValueToCard(List<Integer> cardValues) {
        return cardValues
                .stream()
                .map(cardValue ->
                        aCard()
                                .withValue(cardValue.toString())
                                .build())
                .collect(toList());

    }
}
