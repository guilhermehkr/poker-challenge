package com.poker.challenge;

public class Play {

    private String playerOneHand;
    private String playerTwoHand;

    public Play(String playerOneHand, String playerTwoHand) {
        this.playerOneHand = playerOneHand;
        this.playerTwoHand = playerTwoHand;
    }

    public void setPlayerOneHand(String playerOneHand) {
        this.playerOneHand = playerOneHand;
    }

    public void setPlayerTwoHand(String playerTwoHand) {
        this.playerTwoHand = playerTwoHand;
    }
}
