package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.combination.Rank;
import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.card.Card;
import com.poker.challenge.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static com.poker.challenge.combination.Rank.*;
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

        List<Card> sortedPlayerOneCards = super.collectPlayersCardInOrderOfImportance(playerOneCards);
        List<Card> sortedPlayerTwoCards = super.collectPlayersCardInOrderOfImportance(playerTwoCards);

        Integer playerOnePair = super.getFirstPlayersCard(sortedPlayerOneCards);
        Integer playerTwoPair = super.getFirstPlayersCard(sortedPlayerTwoCards);

        return RoundResult.decideWhoTheWinnerIs(
                playerOnePair,
                playerTwoPair,
                () -> highCardTieBreaker.breakTie(
                        tail(sortedPlayerOneCards),
                        tail(sortedPlayerTwoCards)
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
}
