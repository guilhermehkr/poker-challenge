package com.poker.challenge.infrastructure;

import com.poker.challenge.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokerCommandLineRunner implements CommandLineRunner {

    private GameService gameService;
    private FileReader fileReader;

    @Autowired
    public PokerCommandLineRunner(GameService gameService, FileReader fileReader) {
        this.gameService = gameService;
        this.fileReader = fileReader;
    }

    @Override
    public void run(String... args) throws Exception {

        List<String> hands = fileReader.readFileFrom(System.in);
        gameService.start(hands);
    }
}