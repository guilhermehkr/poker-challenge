package com.poker.challenge.combination;

import com.google.common.collect.PeekingIterator;
import com.poker.challenge.round.card.Card;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Iterators.peekingIterator;
import static com.poker.challenge.util.ListUtil.tail;
import static java.util.Comparator.naturalOrder;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Component
public class Straight implements Combination {

    @Override
    public boolean checkCombination(List<Card> cards) {

        return isNotEmpty(cards)
                && cards.size() == 5
                && checkStraightCombination(cards);
    }

    @Override
    public Rank getRank() {
        return Rank.Straight;
    }

    @Override
    public int getOrder() {
        return Rank.Straight.getSpringOrder();
    }

    private boolean checkStraightCombination(List<Card> cards) {

        if (cards.size() == 1) {
            return true;
        }

        cards.sort(naturalOrder());
        PeekingIterator<Card> cardsIterator = peekingIterator(cards.iterator());

        Card currentCard = cardsIterator.next();
        Card nextCard = cardsIterator.peek();

        return cardsIterator.hasNext()
                && currentCard.getValueAsInt() == nextCard.getValueAsInt() - 1
                && checkStraightCombination(tail(cards));
    }
}
