package com.poker.challenge.service;

import com.poker.challenge.combination.RankService;
import com.poker.challenge.combination.Rank;
import com.poker.challenge.combination.tiebreaker.TieBreakerFactory;
import com.poker.challenge.round.Round;
import com.poker.challenge.round.RoundResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.groupingBy;

@Service
public class PokerService {

    private RankService rankService;

    private TieBreakerFactory tieBreakerFactory;

    @Autowired
    public PokerService(RankService rankService, TieBreakerFactory tieBreakerFactory) {
        this.rankService = rankService;
        this.tieBreakerFactory = tieBreakerFactory;
    }

    public RoundResult play(Round round) {

        Rank playerOneCombination = rankService.findBestCombination(round.getPlayerOneCards());
        Rank playerTwoCombination = rankService.findBestCombination(round.getPlayerTwoCards());

        return RoundResult.decideWhoTheWinnerIs(
                playerOneCombination.getRankNumber(),
                playerTwoCombination.getRankNumber(),
                () -> tieBreakerFactory
                        .newTieBreaker(playerOneCombination)
                        .breakTie(round.getPlayerOneCards(), round.getPlayerTwoCards())
        );

        /*RoundResult roundResult;
        if (playerOneCombination.getRankNumber() > playerTwoCombination.getRankNumber()) {
            roundResult = RoundResult.PlayerOne;
        } else if (playerTwoCombination.getRankNumber() > playerOneCombination.getRankNumber()) {
            roundResult = RoundResult.PlayerTwo;
        } else {
            roundResult =
                    tieBreakerFactory
                            .newTieBreaker(playerOneCombination)
                            .breakTie(round.getPlayerOneCards(), round.getPlayerTwoCards());

        }

        return roundResult;*/
    }
}