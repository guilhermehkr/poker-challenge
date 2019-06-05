package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;

import java.util.List;

public interface Combination {

    boolean checkCombination(List<Card> cards);

    Rank getRank();
}