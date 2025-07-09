package org.example.controller;
import org.example.model.AnswerOption;
import org.example.model.Card;
import org.example.util.JSONUtils;
import org.example.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.example.util.SpacedRepetition;

import java.io.IOException;
import java.util.*;
public class CardviewController {
    @FXML private Label questionLabel;
    @FXML private VBox anwsersBox;
    @FXML private Label feedbackLabel;
    private List<Card> cards;
    private int currentIndex=0;
    private String jsonPath;
    @FXML
    public void initialize(){
        int userId=Session.getUserId();
        jsonPath="/userdata" + userId + "_cards.json";
        try {
            cards=JSONUtils.loadCardsFromFile(jsonPath);
            if (cards.isEmpty()) {
                questionLabel.setText("Keine Karteikarten vorhanden. Bitte neu anlegen");
            } else {
                showCard(cards.get(currentIndex));
            }
        } catch (IOException e) {
            questionLabel.setText("Fehler beim Laden der Karten, probiere es erneut");
            e.printStackTrace();
        }
    }
    private void showCard(Card card) {
        questionLabel.setText(card.getQuestion());
        anwsersBox.getChildren().clear();
        for (AnswerOption option :card.getAnswerOptions()){
            CheckBox checkBox = new CheckBox(option.getText());
            checkBox.setUserData(option);
            anwsersBox.getChildren().add(checkBox);
        }
        feedbackLabel.setText("");
    }
    // Hier "Spaced Repetition" tatsächlich SuperMemo2 weil einfacher zu implementieren
    private void updateCardDifficulty(Card card, int grade) {
        SpacedRepetition.apply(card, grade);
    }
    private void saveProgress(){
        try {
            JSONUtils.saveCardstoFile(jsonPath,cards);
        } catch (IOException e) {
            feedbackLabel.setText("Fehler beim Speichern");
        }
    }
    private void showNextCard(){
        currentIndex++;
        if (currentIndex<cards.size()){
            showCard(cards.get(currentIndex));
        } else {
            questionLabel.setText("Du hast alle Karten bearbeitet Glückwunsch");
            anwsersBox.getChildren().clear();
        }
    }
    // Bewertungsaktionen
    @FXML public void onEasy(){
        evaluateCurrentCard(5);
    }
    @FXML public void onHard(){
        evaluateCurrentCard(3);
    }
    @FXML public void onveryHard(){
        evaluateCurrentCard(1);
    }
    @FXML public void onNext(){
        showNextCard();
    }
    private void evaluateCurrentCard(int grade) {
        Card card= cards.get(currentIndex);
        updateCardDifficulty(card,grade);
        saveProgress();
        feedbackLabel.setText("Bewertet mit Schwierigkeit"+grade+"checked");
    }


}
