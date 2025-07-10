package org.example.controller;
import javafx.scene.Parent;
import org.example.util.DatabaseConnector;
import org.example.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static org.example.util.Session.setUserId;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;
    @FXML
    // Wenn Login erfolgreich Übergang ins Dashboard
    private void openDashboard() {
        System.out.println("Login erfolgreich, Benuter-ID lautet:" +Session.getUserId());
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/dashboard.fxml"));
            Parent root =loader.load();
            DashboardController dashboardController = loader.getController();
            setUserId(Session.getUserId());
            Stage stage=new Stage();
            stage.setTitle("Deine Themenübersicht");
            stage.setScene(new Scene(root));
            stage.show();
            // Login Fenster schließen
        Stage currentStage=(Stage) usernameField.getScene().getWindow();
        currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onLogin() {
        String username=usernameField.getText();
        String password=passwordField.getText();
        int userId=DatabaseConnector.loginUser(username,password);
        if (userId>0) {
            setUserId(userId);
            openDashboard();
        } else {
            statusLabel.setText("Falscher Benutzername oder falsches Passwort");
        }
    }
    public void onOpenRegister() {
        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/register.fxml")));
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
