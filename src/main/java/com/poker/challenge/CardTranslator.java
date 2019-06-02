package com.poker.challenge;

import org.springframework.stereotype.Component;

import java.util.List;

import static com.poker.challenge.Card.newCard;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@Component
public class CardTranslator {

    public final static String TEN = "T";
    public final static String TEN_NUMBER = "10";

    public final static String JACK = "J";
    public final static String JACK_NUMBER = "11";

    public final static String QUEEN = "Q";
    public final static String QUEEN_NUMBER = "12";

    public final static String KING = "K";
    public final static String KING_NUMBER = "13";

    public final static String ACE = "A";
    public final static String ACE_NUMBER = "14";

    public Play translate(Play play) {

        List<Card> playerOneCards = replace(play.getPlayerOneCards());
        List<Card> playerTwoCards = replace(play.getPlayerTwoCards());

        return new Play(playerOneCards, playerTwoCards);
    }

    private List<Card> replace(List<Card> playerCards) {
        return emptyIfNull(playerCards)
                .stream()
                .map(card -> newCard(card.getValue().replace(TEN, TEN_NUMBER), card.getSuit()))
                .map(card -> newCard(card.getValue().replace(JACK, JACK_NUMBER), card.getSuit()))
                .map(card -> newCard(card.getValue().replace(QUEEN, QUEEN_NUMBER), card.getSuit()))
                .map(card -> newCard(card.getValue().replace(KING, KING_NUMBER), card.getSuit()))
                .map(card -> newCard(card.getValue().replace(ACE, ACE_NUMBER), card.getSuit()))
                .sorted()
                .collect(toList());
    }
}
