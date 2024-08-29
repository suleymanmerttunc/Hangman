package com.example.hangman;

public class HangmanGame {
    private static String secretWord;
    private StringBuilder currentGuess;
    private int remainingGuesses;
    private boolean gameOver;
    private boolean gameWon;

    public static String getSecretWord() {
        return secretWord;
    }

    private String hangmanPhotos[] = {
            "/images/Hangman_Picture2.png",
            "/images/Hangman_Picture3.png",
            "/images/Hangman_Picture4.png",
            "/images/Hangman_Picture5.png",
            "/images/Hangman_Picture6.png",
            "/images/Hangman_Picture7.png"};

    private String hangwomenPhotos[] = {
            "/images/Hangman_Picture_Women2.png",
            "/images/Hangman_Picture_Women3.png",
            "/images/Hangman_Picture_Women4.png",
            "/images/Hangman_Picture_Women5.png",
            "/images/Hangman_Picture_Women6.png",
            "/images/Hangman_Picture_Women7.png"};

    public HangmanGame(String word) {
        secretWord = word.toLowerCase();
        currentGuess = new StringBuilder("_".repeat(secretWord.length()));
        remainingGuesses = 6;
        gameOver = false;
        gameWon = false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public String getCurrentGuess() {
        return currentGuess.toString();
    }

    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    public void guess(char letter) {
        if (!gameOver) {
            boolean found = false;
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == letter) {
                    currentGuess.setCharAt(i, letter);
                    found = true;
                }
            }
            if (!found) {
                remainingGuesses--;
            }
            checkGameOver();
        }
    }

    private void checkGameOver() {
        if (remainingGuesses <= 0) {
            gameOver = true;
        }
        if (currentGuess.indexOf("_") == -1) {
            gameOver = true;
            gameWon = true;
        }
    }
    public String getHangmanPhoto(int index) {
        return hangmanPhotos[index];
    }
    public String getHangwomanPhoto(int index) {
        return hangwomenPhotos[index];
    }
}