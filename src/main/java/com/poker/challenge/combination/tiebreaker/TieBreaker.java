package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.card.Card;

import java.util.List;

public interface TieBreaker {

    RoundResult breakTie(List<Card> playerOneCards, List<Card> playerTwoCards);
}
