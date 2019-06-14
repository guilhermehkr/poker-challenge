package com.poker.challenge.service.printer;

import com.poker.challenge.round.RoundResult;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.poker.challenge.round.RoundResult.PlayerOne;
import static com.poker.challenge.round.RoundResult.PlayerTwo;
import static com.poker.challenge.round.RoundResult.Tie;

@Component
public class GameReportPrinter {
    // #IMPROVEMENT-3

    public void printResultReport(Map<RoundResult, Long> report) {
        System.out.println("-----------------------------");
        System.out.println("Player 1: " + report.get(PlayerOne));
        System.out.println("Player 2: " + report.get(PlayerTwo));
        System.out.println("Tie: " + report.getOrDefault(Tie, 0L));
        System.out.println("-----------------------------");
        System.out.println("by Guilherme Gambeti");
    }
}
