package org.example.controller;
import javafx.event.ActionEvent;
import org.example.model.Card;
import org.example.util.DatabaseConnector;
import org.example.util.JSONUtils;
import org.example.util.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardController {

    @FXML private Label usernameLabel;
    @FXML private TextField searchField;
    @FXML private ListView<String> cardListView;
    @FXML private Label statusLabel;
    private List<Card> allCards;
    @FXML
    String getUsernameFromDatabase(int userId) {
        String username = null;
        String query = "SELECT username FROM users WHERE id = ?";
        try(Connection connection = DatabaseConnector.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                username = resultSet.getString("username");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return username;
    }

    public void initialize() {
        int userId=Session.getUserId();
        String username=getUsernameFromDatabase(userId);
        usernameLabel.setText(username);
        onViewCards(userId);
    }
    @FXML
    private void onViewCards() {onViewCards(Session.getUserId());}
    @FXML
    private void onViewCards(int userId) {
        String path ="userdata/"+userId+"_cards.json";
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("Datei nicht gefunden: " + path);
            statusLabel.setText("Noch keine Karten vorhanden.");
            allCards = new ArrayList<>();
            updateListView(allCards);
            return;
        }
        try {
            allCards=JSONUtils.loadCardsFromFile(path);
            updateListView(allCards);
        } catch (IOException e) {
            statusLabel.setText("Fehler beim laden der Karten!");
            e.printStackTrace();
        }
    }
    private  void updateListView(List<Card> cards){
        ObservableList<String> items=FXCollections.observableArrayList();
        for (Card c :cards) {
            items.add(c.getTopic()+"-"+c.getQuestion());
            cardListView.setItems(items);
        }

    }
    @FXML
    public void onSerach() {
        String query=searchField.getText().trim().toLowerCase();
        List<Card> filtered=allCards.stream()
                .filter(c->c.getTopic().toLowerCase().contains(query))
                .collect(Collectors.toList());
        updateListView(filtered);
    }
    @FXML
    public void onStartLearning(){
        // Karten werden an Lernansicht übergeben
        try {
            Stage stage=(Stage) usernameLabel.getScene().getWindow();
            Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/fxml/card_viewer.fxml")));
            stage.setScene(scene);
        } catch (IOException e) {
            statusLabel.setText("Lernansicht konnte nicht geladen werden!");
        }
    }
    @FXML
    public void onLogout(){
        Session.setUserId(-1);
        try {
            Stage stage=(Stage) usernameLabel.getScene().getWindow();
            Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/fxml/login.fxml")));
            stage.setScene(scene);
        }   catch (IOException e) {
            e.printStackTrace();
        }

    }
}