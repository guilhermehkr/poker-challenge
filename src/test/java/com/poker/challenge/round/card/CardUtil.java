package com.poker.challenge.round.card;

import com.poker.challenge.round.hand.HandToCardsTransformer;

import java.util.List;

public class CardUtil {

    private static HandToCardsTransformer handToCardsTransformer = new HandToCardsTransformer();

    public static List<Card> createCards(String hand) {
        return handToCardsTransformer.transform(hand);
    }
}
