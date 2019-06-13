package com.poker.challenge.round.hand;

import com.poker.challenge.round.card.Card;
import org.apache.commons.collections4.Transformer;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.base.Splitter.on;
import static com.poker.challenge.round.card.CardBuilder.aCard;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang.StringUtils.isNotBlank;

@Component
public class HandToCardsTransformer implements Transformer<String, List<Card>> {

    public final static String WHITE_SPACE = " ";
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

    public List<Card> transform(String playerHand) {

        return on(WHITE_SPACE)
                .splitToList(playerHand)
                .stream()
                .map(stringCard -> replaceIf(stringCard, TEN, TEN_NUMBER))
                .map(stringCard -> replaceIf(stringCard, JACK, JACK_NUMBER))
                .map(stringCard -> replaceIf(stringCard, QUEEN, QUEEN_NUMBER))
                .map(stringCard -> replaceIf(stringCard, KING, KING_NUMBER))
                .map(stringCard -> replaceIf(stringCard, ACE, ACE_NUMBER))
                .map(stringCard ->
                        aCard()
                                .withStringCard(stringCard)
                                .build())
                .sorted()
                .collect(toList());
    }

    private String replaceIf(String stringCard, String target, String replacement) {

        if (isNotBlank(stringCard)
                && stringCard.contains(target)) {

            return stringCard.replace(target, replacement);
        }
        return stringCard;
    }
}