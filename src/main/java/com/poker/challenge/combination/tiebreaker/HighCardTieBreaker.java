package com.poker.challenge.combination.tiebreaker;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.poker.challenge.combination.Rank;
import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.card.Card;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static com.poker.challenge.combination.Rank.*;
import static com.poker.challenge.util.ListUtil.tail;
import static java.util.Comparator.reverseOrder;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Component
public class HighCardTieBreaker extends BaseTieBreaker implements TieBreaker {

    @Override
    public RoundResult breakTie(List<Card> playerOneCards, List<Card> playerTwoCards) {

        if (isEmpty(playerOneCards) && isEmpty(playerTwoCards)) {
            return RoundResult.Tie;
        }

        Integer highestPlayerOneCard = collectHighestPlayersCard(playerOneCards);
        Integer highestPlayerTwoCard = collectHighestPlayersCard(playerTwoCards);

        return RoundResult.decideWhoTheWinnerIs(
                highestPlayerOneCard,
                highestPlayerTwoCard,
                () -> breakTie(
                        tail(playerOneCards),
                        tail(playerTwoCards)
                )
        );
    }

    @Override
    public Set<Rank> correspondingRanks() {
        return newHashSet(
                HighCard,
                Straight,
                Flush,
                StraightFlush
        );
    }

    private Integer collectHighestPlayersCard(List<Card> playersCards) {
        playersCards.sort(reverseOrder());
        return super.getFirstPlayersCard(playersCards);
    }
}