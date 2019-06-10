package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HighCard implements Combination {

    @Override
    public boolean checkCombination(List<Card> cards) {
        return true;
    }

    @Override
    public Rank getRank() {
        return Rank.HighCard;
    }

    @Override
    public int getOrder() {
        return Rank.HighCard.getSpringOrder();
    }
}
