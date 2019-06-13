package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ThreeOfAKind extends NumberOfAKind implements Combination {

    private final static Long THREE_OF_A_KIND = 3L;

    @Override
    public boolean checkCombination(List<Card> cards) {
        return super.checkIf(cards, ONE_COMBINATION_OF, THREE_OF_A_KIND);
    }

    @Override
    public Rank getRank() {
        return Rank.ThreeOfAKind;
    }

    @Override
    public int getOrder() {
        return Rank.ThreeOfAKind.getSpringOrder();
    }
}
