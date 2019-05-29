package com.poker.challenge;

import org.springframework.stereotype.Component;

@Component
public class HandSplitter {

    public Play splitHands(String hands) {
        String[] split = hands.split("");
        return new Play(split[0], split[1]);
    }
}
