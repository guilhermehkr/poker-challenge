package com.poker.challenge.service;

import com.poker.challenge.combination.RankProvider;
import com.poker.challenge.combination.Rank;
import com.poker.challenge.combination.tiebreaker.TieBreakerFactory;
import com.poker.challenge.round.Round;
import com.poker.challenge.round.RoundResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.groupingBy;

@Service
public class PokerService {

    private RankProvider rankProvider;

    private TieBreakerFactory tieBreakerFactory;

    @Autowired
    public PokerService(RankProvider rankProvider, TieBreakerFactory tieBreakerFactory) {
        this.rankProvider = rankProvider;
        this.tieBreakerFactory = tieBreakerFactory;
    }

    public RoundResult play(Round round) {

        Rank playerOneRank = rankProvider.findBestRank(round.getPlayerOneCards());
        Rank playerTwoRank = rankProvider.findBestRank(round.getPlayerTwoCards());

        return RoundResult.decideWhoTheWinnerIs(
                playerOneRank.getRankNumber(),
                playerTwoRank.getRankNumber(),
                () -> tieBreakerFactory
                        .newTieBreaker(playerOneRank)
                        .breakTie(round.getPlayerOneCards(), round.getPlayerTwoCards())
        );
    }
}