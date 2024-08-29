package com.example.hangman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class HangwomenController {
    @FXML
    private TextField guessField;

    @FXML
    private Label remainingLabel;

    @FXML
    private Label wordLabel;

    private HangmanGame game;
    @FXML
    private ImageView hangwomenImageView;



    @FXML
    public void initialize() {
        game = new HangmanGame(new WordList().getRandomWord());
        updateUI();
    }

    @FXML
    private void handleGuess() {
        if (!game.isGameOver()) {
            String input = guessField.getText().toLowerCase();
            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                game.guess(input.charAt(0));
                updateUI();
            } else {
                showAlert("Invalid guess. Please enter a single letter.");
            }
            guessField.clear();
        } else {
            showAlert("The game is over. Start a new game.");
        }
    }

    private void updateUI() {
        wordLabel.setText(game.getCurrentGuess());
        remainingLabel.setText("Remaining guesses: " + game.getRemainingGuesses());
        updateHangwomanImage();
        if (game.isGameOver()) {
            if (game.isGameWon()) {
                showAlert("Congratulations! You won!");
            } else {
                showAlert("Game over. The word was: " + HangmanGame.getSecretWord());
            }
        }
    }
    private void updateHangwomanImage() {
        int index = 6 - game.getRemainingGuesses();
        if (index > 0 && index <= 6) {
            try {
                String imagePath2 = game.getHangwomanPhoto(index - 1); // Get the appropriate image path
                InputStream inputStream = getClass().getResourceAsStream(imagePath2);
                if (inputStream != null) {
                    Image image2 = new Image(inputStream);
                    hangwomenImageView.setImage(image2);
                } else {
                    System.err.println("Image file not found: " + imagePath2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void BackHomePageOnMouseClicked(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
            Stage stage = (Stage) scene.getWindow();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void restartGameOnMouseClicked(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hangwomen.fxml"));
            Parent root = loader.load();

            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
            Stage stage = (Stage) scene.getWindow();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}