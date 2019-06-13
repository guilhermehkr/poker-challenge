package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

import static com.google.common.collect.Iterables.getLast;
import static java.util.Comparator.naturalOrder;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Component
public class RoyalFlush implements Combination {

    private StraightFlush straightFlush;

    @Autowired
    public RoyalFlush(StraightFlush straightFlush) {
        this.straightFlush = straightFlush;
    }

    @Override
    public boolean checkCombination(List<Card> cards) {
        return isNotEmpty(cards)
                && isLastCardAnAce(cards)
                && straightFlush.checkCombination(cards);
    }

    @Override
    public Rank getRank() {
        return Rank.RoyalFlush;
    }

    @Override
    public int getOrder() {
        return Rank.RoyalFlush.getSpringOrder();
    }

    private boolean isLastCardAnAce(List<Card> cards) {
        cards.sort(naturalOrder());
        Card lastCard = getLast(cards);
        return lastCard != null
                && lastCard.isAnAce();
    }
}
