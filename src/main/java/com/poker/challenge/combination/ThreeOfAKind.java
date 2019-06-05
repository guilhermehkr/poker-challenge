package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;

import java.util.List;

public class ThreeOfAKind extends NumberOfAKind implements Combination {

    public final static int THREE_OF_A_KIND = 3;

    @Override
    public boolean checkCombination(List<Card> cards) {
        return super.checkIf(cards, THREE_OF_A_KIND, NUMBER_OF_COMBINATIONS);
    }

    @Override
    public Rank getRank() {
        return Rank.ThreeOfAKind;
    }
}
