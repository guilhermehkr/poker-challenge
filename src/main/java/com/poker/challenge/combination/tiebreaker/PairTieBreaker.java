package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.combination.Rank;
import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.card.Card;
import com.poker.challenge.round.card.CardBuilder;
import com.poker.challenge.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.collect.Sets.newHashSet;
import static com.poker.challenge.combination.Rank.*;
import static com.poker.challenge.round.card.CardBuilder.aCard;
import static com.poker.challenge.util.ListUtil.head;
import static com.poker.challenge.util.ListUtil.tail;
import static java.util.Comparator.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@Component
public class PairTieBreaker extends BaseTieBreaker implements TieBreaker {

    private HighCardTieBreaker highCardTieBreaker;

    @Autowired
    public PairTieBreaker(HighCardTieBreaker highCardTieBreaker) {
        this.highCardTieBreaker = highCardTieBreaker;
    }

    @Override
    public RoundResult breakTie(List<Card> playerOneCards, List<Card> playerTwoCards) {

        final List<Integer> sortedPlayerOneCards = collectPlayersCardInOrderOfImportance(playerOneCards);
        final List<Integer> sortedPlayerTwoCards = collectPlayersCardInOrderOfImportance(playerTwoCards);

        Integer playerOnePair = head(sortedPlayerOneCards).orElse(DEFAULT_CARD_VALUE);
        Integer playerTwoPair = head(sortedPlayerTwoCards).orElse(DEFAULT_CARD_VALUE);

        return RoundResult.decideWhoTheWinnerIs(
                playerOnePair,
                playerTwoPair,
                () -> highCardTieBreaker.breakTie(
                        tail(mapValueToCard(sortedPlayerOneCards)),
                        tail(mapValueToCard(sortedPlayerTwoCards))
                )
        );
    }

    @Override
    public Set<Rank> correspondingRanks() {
        return newHashSet(
                Pair,
                ThreeOfAKind,
                FullHouse,
                FourOfAKind
        );
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
