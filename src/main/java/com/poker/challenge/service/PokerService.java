package com.poker.challenge.service;

import com.poker.challenge.round.card.Card;
import com.poker.challenge.round.Round;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@Service
public class PokerService {

    public Round play(Round round) {

        return null;
        /*Map<String, Integer> report = new HashMap<>();
        round.getPlayerOneCards()
                .forEach(card -> {
                    Integer count = report.get(card.getValue()) == null ? 0 : report.get(card.getValue());
                    report.put(card.getValue(), ++count);
                });

        List<String> values = round.getPlayerOneCards()
                .stream()
                .map(Card::getValue)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(report);

        Map<String, Integer> combination = new HashMap<>();

        values.forEach(value -> {

            if (report.get(value) == 2) {
                Integer numeberOfPairs = combination.get("pair") == null ? 0 : combination.get("pair");
                combination.put("pair", ++numeberOfPairs);
            } else if (report.get(value) == 3) {
                Integer numeberOfPairs = combination.get("three") == null ? 0 : combination.get("three");
                combination.put("three", ++numeberOfPairs);
            } else if (report.get(value) == 4) {
                System.out.println("Four of a kind");
            }
        });

        System.out.println(combination);*/
    }
}