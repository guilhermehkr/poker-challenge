package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;

import java.util.List;

public class FourOfAKind extends NumberOfAKind implements Combination {

    public final static Long FOUR_OF_A_KIND = 4L;

    @Override
    public boolean checkCombination(List<Card> cards) {
        return super.checkIf(cards, FOUR_OF_A_KIND, NUMBER_OF_COMBINATIONS);
    }

    @Override
    public Rank getRank() {
        return Rank.FourOfAKind;
    }
}
