package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Pair extends NumberOfAKind implements Combination {

    final static Long PAIR = 2L;

    @Override
    public boolean checkCombination(List<Card> cards) {
        return super.checkIf(cards, ONE_COMBINATION_OF, PAIR);
    }

    @Override
    public Rank getRank() {
        return Rank.Pair;
    }

    @Override
    public int getOrder() {
        return Rank.Pair.getSpringOrder();
    }
}
