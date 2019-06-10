package com.poker.challenge.combination;

public enum Rank {

    HighCard(1),
    Pair(2),
    TwoPairs(3),
    ThreeOfAKind(4),
    Straight(5),
    Flush(6),
    FullHouse(7),
    FourOfAKind(8),
    StraightFlush(9),
    RoyalFlush(10);

    private int rankNumber;

    Rank(int rankNumber) {
        this.rankNumber = rankNumber;
    }

    public int getRankNumber() {
        return rankNumber;
    }

    public int getSpringOrder() {
        return rankNumber * -1;
    }
}
