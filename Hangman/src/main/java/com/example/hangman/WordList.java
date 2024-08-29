package com.example.hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class WordList {
    private ArrayList<String> words;
    private Random random;

    public WordList() {
        words = new ArrayList<>();
        random = new Random();
        loadWordsFromFile("/com/example/hangman/words.txt");
    }

    private void loadWordsFromFile(String filename) {
        try (InputStream inputStream = getClass().getResourceAsStream(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    words.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading word list from file: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("File not found: " + filename);
        }
    }

    public String getRandomWord() {
        if (words.isEmpty()) {
            throw new IllegalArgumentException("Word list is empty.");
        }
        int index = random.nextInt(words.size());
        return words.get(index);
    }
}
