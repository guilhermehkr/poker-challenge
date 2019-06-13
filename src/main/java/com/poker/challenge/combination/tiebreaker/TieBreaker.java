package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.combination.Rank;
import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.card.Card;

import java.util.List;
import java.util.Set;

public interface TieBreaker {

    Integer DEFAULT_CARD_VALUE = 0;

    RoundResult breakTie(List<Card> playerOneCards, List<Card> playerTwoCards);

    Set<Rank> correspondingRanks();
}
