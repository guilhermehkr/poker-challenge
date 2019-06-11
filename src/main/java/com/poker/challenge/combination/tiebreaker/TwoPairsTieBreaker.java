package com.poker.challenge.combination.tiebreaker;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.poker.challenge.combination.Rank;
import com.poker.challenge.round.RoundResult;
import com.poker.challenge.round.card.Card;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;

@Component
public class TwoPairsTieBreaker extends BaseTieBreaker implements TieBreaker {

    @Override
    public RoundResult breakTie(List<Card> playerOneCards, List<Card> playerTwoCards) {

        List<Card> sortedPlayerOneCards = super.collectPlayersCardInOrderOfImportance(playerOneCards);
        List<Card> sortedPlayerTwoCards = super.collectPlayersCardInOrderOfImportance(playerTwoCards);

        List<Card> twoPairsOfPlayerOne = getTwoPairsInOrder(sortedPlayerOneCards);
        List<Card> twoPairsOfPlayerTwo = getTwoPairsInOrder(sortedPlayerTwoCards);

        RoundResult roundResult = RoundResult.Tie;

        for (int index = 0; index < twoPairsOfPlayerOne.size(); index++) {
            Card card = twoPairsOfPlayerOne.get(index);
            Card card2 = twoPairsOfPlayerTwo.get(index);

            roundResult = RoundResult.decideWhoTheWinnerIs(
                    card.getValueAsInt(),
                    card2.getValueAsInt(),
                    () -> RoundResult.Tie
            );

            if (!roundResult.equals(RoundResult.Tie)) {
                break;
            }
        }

        if (roundResult.equals(RoundResult.Tie)) {
            Card last1 = Iterables.getLast(sortedPlayerOneCards);
            Card last2 = Iterables.getLast(sortedPlayerTwoCards);

            roundResult = RoundResult.decideWhoTheWinnerIs(
                    last1.getValueAsInt(),
                    last2.getValueAsInt(),
                    () -> RoundResult.Tie
            );
        }

        return roundResult;
    }

    @Override
    public Set<Rank> correspondingRanks() {
        return Sets.newHashSet(Rank.TwoPairs);
    }

    private List<Card> getTwoPairsInOrder(List<Card> playersCards) {
        return playersCards
                .subList(0, 2)
                .stream()
                .sorted(reverseOrder())
                .collect(toList());
    }
}
