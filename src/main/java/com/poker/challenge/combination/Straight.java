package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.Iterables.getLast;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Component
public class Straight implements Combination {

    @Override
    public boolean checkCombination(List<Card> cards) {

        boolean result = false;
        if (isNotEmpty(cards)) {

            List<Card> sortedCards = cards
                    .stream()
                    .sorted()
                    .collect(toList());

            Card first = sortedCards.iterator().next();
            Card last = getLast(sortedCards);

            result = first.getValueAsInt() == (last.getValueAsInt() - 4);
        }
        return result;
    }

    @Override
    public Rank getRank() {
        return Rank.Straight;
    }

    @Override
    public int getOrder() {
        return Rank.Straight.getSpringOrder();
    }
}
