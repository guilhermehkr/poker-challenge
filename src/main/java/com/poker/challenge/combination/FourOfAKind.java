package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FourOfAKind extends NumberOfAKind implements Combination {

    public final static Long FOUR_OF_A_KIND = 4L;

    @Override
    public boolean checkCombination(List<Card> cards) {
        return super.checkIf(cards, ONE_COMBINATION_OF, FOUR_OF_A_KIND);
    }

    @Override
    public Rank getRank() {
        return Rank.FourOfAKind;
    }

    @Override
    public int getOrder() {
        return Rank.FourOfAKind.getSpringOrder();
    }
}
