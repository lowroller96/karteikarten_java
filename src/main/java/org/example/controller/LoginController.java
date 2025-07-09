package org.example.controller;
import org.example.util.DatabaseConnector;
import org.example.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;
    @FXML
    public void onLogin() {
        String username=usernameField.getText();
        String password=passwordField.getText();
        int userId=DatabaseConnector.loginUser(username,password);
        if (userId>0) {
            Session.setUserId(userId);
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
        private void openDashboard() {
            System.out.println("Login erfolgreich, Benuter-ID lautet:" +Session.getUserId());
            //TODO: dashboard.fxml laden !!
        }
    }
}
