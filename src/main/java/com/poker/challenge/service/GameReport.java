package com.poker.challenge.service;

public class GameReport {

    private Integer playOneVictories = 0;
    private Integer playTwoVictories = 0;
    private Integer tie = 0;

    public void addVictoryToPlayOne() {
        this.playOneVictories++;
    }

    public void addVictoryToPlayTwo() {
        this.playTwoVictories++;
    }

    public void addTie() {
        this.tie++;
    }

    public Integer getPlayOneVictories() {
        return playOneVictories;
    }

    public Integer getPlayTwoVictories() {
        return playTwoVictories;
    }

    public Integer getTie() {
        return tie;
    }
}
