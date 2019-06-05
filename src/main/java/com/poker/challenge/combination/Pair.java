package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;

import java.util.List;

public class Pair extends NumberOfAKind implements Combination {

    public final static int PAIR = 2;

    @Override
    public boolean checkCombination(List<Card> cards) {
        return super.checkIf(cards, PAIR, NUMBER_OF_COMBINATIONS);
    }

    @Override
    public Rank getRank() {
        return Rank.Pair;
    }
}