package org.example.controller;
import org.example.model.Card;
import org.example.util.JSONUtils;
import org.example.util.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardController {

    @FXML private Label usernameLabel;
    @FXML private TextField searchField;
    @FXML private ListView<String> cardListView;
    @FXML private Label statusLabel;
    private List<Card> allCards;
    @FXML
    public void initialize() {
        int userId=Session.getUserId();
        String username=getUsernameFromDatabase(userId);
        usernameLabel.setText(username);
        loadUserCards(UserId);
    }
    private void loadUserCards(int userId) {
        String path ="userdata/"+userId+"_cards.json";
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
    public String onLogout(){
        Session.setUserId(-1);
        try {
            Stage stage=(Stage) usernameLabel.getScene().getWindow();
            Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/fxml/login.fxml)));
                    stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    String getUsernameFromDatabse(int userId); {
            return "Benutzer "+userId;
        }
    }


}
