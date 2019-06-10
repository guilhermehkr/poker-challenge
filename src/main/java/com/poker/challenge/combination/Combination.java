package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.springframework.core.Ordered;

import java.util.List;

public interface Combination extends Ordered {

    boolean checkCombination(List<Card> cards);

    Rank getRank();
}