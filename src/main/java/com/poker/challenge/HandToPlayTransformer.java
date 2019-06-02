package com.poker.challenge;

import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.base.Splitter.on;
import static com.poker.challenge.CardBuilder.aCard;
import static com.poker.challenge.PlayBuilder.aPlay;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang.StringUtils.isBlank;


@Component
public class HandToPlayTransformer {

    public final static int TOTAL_CHARACTERS = 29;
    public final static int PLAYER_ONE_HAND_START = 0;
    public final static int PLAYER_ONE_HAND_END = 14;
    public final static int PLAYER_TWO_HAND_START = 15;
    public final static int PLAYER_TWO_HAND_END = 29;

    public final static String WHITE_SPACE = " ";

    public Play transform(String hands) {

        boolean isInvalid = isAnInvalidRound(hands);
        PlayBuilder builder =
                aPlay().withIsInvalidHand(isInvalid);

        if (!isInvalid) {

            String playerOneHand = hands.substring(PLAYER_ONE_HAND_START, PLAYER_ONE_HAND_END);
            String playerTwoHand = hands.substring(PLAYER_TWO_HAND_START, PLAYER_TWO_HAND_END);

            List<Card> playerOneCards = transformToCards(playerOneHand);
            List<Card> playerTwoCards = transformToCards(playerTwoHand);

            builder.withPlayerOneCards(playerOneCards)
                    .withPlayerTwoCards(playerTwoCards)
                    .build();
        }
        return builder.build();
    }

    private boolean isAnInvalidRound(String hands) {
        return isBlank(hands) || hands.length() != TOTAL_CHARACTERS; // #IMPROVEMENT-1
    }

    private List<Card> transformToCards(String playerHand) {
        return on(WHITE_SPACE)
                .splitToList(playerHand)
                .stream()
                .map(stringCard -> aCard().withStringCard(stringCard).build())
                .collect(toList());
    }
}