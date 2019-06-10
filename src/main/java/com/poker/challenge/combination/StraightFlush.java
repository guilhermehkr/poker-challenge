package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StraightFlush implements Combination {

    private Straight straight;
    private Flush flush;

    @Autowired
    public StraightFlush(Straight straight, Flush flush) {
        this.straight = straight;
        this.flush = flush;
    }

    @Override
    public boolean checkCombination(List<Card> cards) {
        return straight.checkCombination(cards)
                && flush.checkCombination(cards);
    }

    @Override
    public Rank getRank() {
        return Rank.StraightFlush;
    }

    @Override
    public int getOrder() {
        return Rank.StraightFlush.getSpringOrder();
    }
}
