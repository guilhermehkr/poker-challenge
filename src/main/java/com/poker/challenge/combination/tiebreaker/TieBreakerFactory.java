package com.poker.challenge.combination.tiebreaker;

import com.poker.challenge.combination.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TieBreakerFactory {

    private Map<Rank, TieBreaker> tieBreakers;

    public TieBreaker newTieBreaker(Rank rank) {
        return tieBreakers.get(rank);
    }

    @Autowired
    public void setTieBreakers(List<TieBreaker> injectedTieBreakers) {

        tieBreakers = new HashMap<>();
        injectedTieBreakers
                .forEach(tieBreaker ->
                        tieBreaker
                                .correspondingRanks()
                                .forEach(rank -> tieBreakers.put(rank, tieBreaker))
                );

        tieBreakers.forEach((rank, tieBreaker) -> System.out.println(rank.toString() + tieBreaker.getClass().getCanonicalName()));
    }
}
