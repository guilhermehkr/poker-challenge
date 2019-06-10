package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.poker.challenge.combination.Pair.PAIR;

@Component
public class TwoPairs extends NumberOfAKind implements Combination {

    protected final static int TWO_COMBINATIONS_OF = 2;

    @Override
    public boolean checkCombination(List<Card> cards) {
        return super.checkIf(cards, TWO_COMBINATIONS_OF, PAIR);
    }

    @Override
    public Rank getRank() {
        return Rank.TwoPairs;
    }

    @Override
    public int getOrder() {
        return Rank.TwoPairs.getSpringOrder();
    }
}
