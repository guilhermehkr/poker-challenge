package com.poker.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class PokerRunner implements CommandLineRunner {


    private PokerService pokerService;

    @Autowired
    public PokerRunner(PokerService pokerService) {
        this.pokerService = pokerService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Poker runner is running");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String x = null;
        while ((x = input.readLine()) != null) {
            System.out.println(x);
        }

        pokerService.play();
    }
}