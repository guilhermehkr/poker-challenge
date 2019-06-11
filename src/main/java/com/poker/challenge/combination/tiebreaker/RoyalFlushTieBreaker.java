package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.combination.Rank;
import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.card.Card;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@Component
public class RoyalFlushTieBreaker implements TieBreaker {

    @Override
    public RoundResult breakTie(List<Card> playerOneCards, List<Card> playerTwoCards) {
        return RoundResult.Tie;
    }

    @Override
    public Set<Rank> correspondingRanks() {
        return newHashSet(Rank.RoyalFlush);
    }
}
