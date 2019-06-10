package com.poker.challenge.service;

import com.poker.challenge.combination.RankService;
import com.poker.challenge.combination.Rank;
import com.poker.challenge.round.Round;
import com.poker.challenge.round.RoundResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.groupingBy;

@Service
public class PokerService {

    private RankService rankService;

    @Autowired
    public PokerService(RankService rankService) {
        this.rankService = rankService;
    }

    public RoundResult play(final Round round) {

        Rank playerOneCombination = rankService.findBestCombination(round.getPlayerOneCards());
        Rank playerTwoCombination = rankService.findBestCombination(round.getPlayerTwoCards());

        RoundResult roundResult;
        if (playerOneCombination == playerTwoCombination) {
            // empatadado
            roundResult = RoundResult.Tie;
        } else if (playerOneCombination.getRankNumber() > playerTwoCombination.getRankNumber()) {
            // player one wins
            roundResult = RoundResult.PlayerOne;
        } else {
            // player two wins
            roundResult = RoundResult.PlayerTwo;
        }

        return roundResult;
    }
}