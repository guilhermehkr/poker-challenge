package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FullHouse implements Combination {

    private ThreeOfAKind threeOfAKind;
    private Pair pair;

    @Autowired
    public FullHouse(ThreeOfAKind threeOfAKind, Pair pair) {
        this.threeOfAKind = threeOfAKind;
        this.pair = pair;
    }

    @Override
    public boolean checkCombination(List<Card> cards) {
        return threeOfAKind.checkCombination(cards)
                && pair.checkCombination(cards);
    }

    @Override
    public Rank getRank() {
        return Rank.FullHouse;
    }

    @Override
    public int getOrder() {
        return Rank.FullHouse.getSpringOrder();
    }
}
