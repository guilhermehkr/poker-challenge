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

        List<Integer> sortedPlayerOneCards = super.collectPlayersCardInOrderOfImportance(playerOneCards);
        List<Integer> sortedPlayerTwoCards = super.collectPlayersCardInOrderOfImportance(playerTwoCards);

        List<Integer> twoPairsOfPlayerOne = getTwoPairsInOrder(sortedPlayerOneCards);
        List<Integer> twoPairsOfPlayerTwo = getTwoPairsInOrder(sortedPlayerTwoCards);

        RoundResult roundResult = RoundResult.Tie;

        for (int index = 0; index < twoPairsOfPlayerOne.size(); index++) {
            Integer card = twoPairsOfPlayerOne.get(index);
            Integer card2 = twoPairsOfPlayerTwo.get(index);

            roundResult = RoundResult.decideWhoTheWinnerIs(
                    card,
                    card2,
                    () -> RoundResult.Tie
            );

            if (!roundResult.equals(RoundResult.Tie)) {
                break;
            }
        }

        if (roundResult.equals(RoundResult.Tie)) {
            Integer last1 = Iterables.getLast(sortedPlayerOneCards);
            Integer last2 = Iterables.getLast(sortedPlayerTwoCards);

            roundResult = RoundResult.decideWhoTheWinnerIs(
                    last1,
                    last2,
                    () -> RoundResult.Tie
            );
        }

        return roundResult;
    }

    @Override
    public Set<Rank> correspondingRanks() {
        return Sets.newHashSet(Rank.TwoPairs);
    }

    private List<Integer> getTwoPairsInOrder(List<Integer> playersCards) {
        return playersCards
                .subList(0, 2)
                .stream()
                .sorted(reverseOrder())
                .collect(toList());
    }
}
