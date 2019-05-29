package com.poker.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokerCommandLineRunner implements CommandLineRunner {

    private PokerService pokerService;
    private FileReader fileReader;

    @Autowired
    public PokerCommandLineRunner(PokerService pokerService, FileReader fileReader) {
        this.pokerService = pokerService;
        this.fileReader = fileReader;
    }

    @Override
    public void run(String... args) throws Exception {

        List<String> hands = fileReader.readFileFrom(System.in);
        pokerService.play(hands);
    }
}