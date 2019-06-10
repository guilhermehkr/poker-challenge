package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@Component
public class Flush implements Combination {

    private final static int ONE_SUIT = 1;

    @Override
    public boolean checkCombination(List<Card> cards) {
        return emptyIfNull(cards)
                .stream()
                .map(Card::getSuit)
                .distinct()
                .count() == ONE_SUIT;
    }

    @Override
    public Rank getRank() {
        return Rank.Flush;
    }

    @Override
    public int getOrder() {
        return Rank.Flush.getSpringOrder();
    }
}
