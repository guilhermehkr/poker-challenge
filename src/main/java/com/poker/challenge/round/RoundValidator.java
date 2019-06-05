package com.poker.challenge.round;

import org.springframework.stereotype.Component;

import static org.apache.commons.lang.StringUtils.isBlank;

@Component
public class RoundValidator {

    final static int TOTAL_CHARACTERS = 29;

    public boolean isAnInvalidRound(String hands) {
        return isBlank(hands) || hands.length() != TOTAL_CHARACTERS; // #IMPROVEMENT-1
    }
}
