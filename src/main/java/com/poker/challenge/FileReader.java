package com.poker.challenge;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

@Component
public class FileReader {

    public List<String> readFileFrom(InputStream input) throws IOException {

        List<String> lines = new LinkedList<>();
        if (input != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line.trim());
                }
            }
        }
        return lines;
    }
}
