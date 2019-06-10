package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Service
public class RankService {

    private List<Combination> combinations;

    public Rank findBestCombination(final List<Card> cards) {
        return combinations
                .stream()
                .filter(combination -> combination.checkCombination(cards))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("HighCard should be the default combination"))
                .getRank();
    }

    @Autowired
    public void setCombinations(List<Combination> combinations) {
        checkArgument(isNotEmpty(combinations), "Spring should wire all Combination instances");
        checkArgument(combinations.size() == Rank.values().length,
                "All combinations should be mapped in com.poker.challenge.combination.Rank");

        this.combinations = combinations;
    }
}
