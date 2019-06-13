package com.poker.challenge.combination;

import com.poker.challenge.round.card.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Service
public class RankProvider {

    static final String NO_DEFAULT_COMBINATION_ERROR_MESSAGE = "HighCard should be the default combination";
    static final String NO_COMBINATIONS_ERROR_MESSAGE = "Spring should wire all Combination instances";
    static final String NO_RANK_MAPPING_COMBINATION_ERROR_MESSAGE = "All combinations should be mapped in com.poker.challenge.combination.Rank";

    private List<Combination> combinations;

    public Rank findBestRank(final List<Card> cards) {
        return combinations
                .stream()
                .filter(combination -> combination.checkCombination(cards))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(NO_DEFAULT_COMBINATION_ERROR_MESSAGE))
                .getRank();
    }

    @Autowired
    public void setCombinations(List<Combination> combinations) {
        checkArgument(isNotEmpty(combinations), NO_COMBINATIONS_ERROR_MESSAGE);
        checkArgument(combinations.size() == Rank.values().length,
                NO_RANK_MAPPING_COMBINATION_ERROR_MESSAGE);

        this.combinations = combinations;
    }
}
