package com.poker.challenge;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.CardBuilder.aCard;

public class CardUtil {

    public static List<Card> createCards(String first, String second, String third, String fourth, String fifth) {
        return newArrayList(
                aCard().withStringCard(first).build(),
                aCard().withStringCard(second).build(),
                aCard().withStringCard(third).build(),
                aCard().withStringCard(fourth).build(),
                aCard().withStringCard(fifth).build()
        );
    }
}
