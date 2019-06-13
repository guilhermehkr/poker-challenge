package com.poker.challenge.round.hand;

import com.poker.challenge.round.RoundValidator;
import com.poker.challenge.round.card.Card;
import com.poker.challenge.round.Round;
import com.poker.challenge.round.RoundBuilder;
import org.apache.commons.collections4.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.poker.challenge.round.RoundBuilder.aRound;


@Component
public class HandsToRoundTransformer implements Transformer<String, Round> {

    public final static int PLAYER_ONE_HAND_START = 0;
    public final static int PLAYER_ONE_HAND_END = 14;
    public final static int PLAYER_TWO_HAND_START = 15;

    private HandToCardsTransformer handToCardsTransformer;
    private RoundValidator roundValidator;

    @Autowired
    public HandsToRoundTransformer(HandToCardsTransformer handToCardsTransformer,
                                   RoundValidator roundValidator) {

        this.handToCardsTransformer = handToCardsTransformer;
        this.roundValidator = roundValidator;
    }

    public Round transform(String twoPlayersHands) {

        boolean isInvalid = roundValidator.isAnInvalidRound(twoPlayersHands);
        RoundBuilder builder =
                aRound().withIsInvalidHand(isInvalid);

        if (!isInvalid) {

            String playerOneHand = twoPlayersHands.substring(PLAYER_ONE_HAND_START, PLAYER_ONE_HAND_END);
            String playerTwoHand = twoPlayersHands.substring(PLAYER_TWO_HAND_START);

            List<Card> playerOneCards = handToCardsTransformer.transform(playerOneHand);
            List<Card> playerTwoCards = handToCardsTransformer.transform(playerTwoHand);

            builder.withPlayerOneCards(playerOneCards)
                    .withPlayerTwoCards(playerTwoCards)
                    .build();
        }
        return builder.build();
    }
}