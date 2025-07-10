package org.example.controller;
import javafx.scene.Parent;
import org.example.util.DatabaseConnector;
import org.example.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.*;

public class RegisterController {
 @FXML private TextField usernameField;
 @FXML private PasswordField passwordField;
 @FXML private Label statusLabel;
 @FXML private Button backButton;
 @FXML
    public void onRegister() {
     String username=usernameField.getText();
     String password=passwordField.getText();

     if (DatabaseConnector.registerUser(username,password)){
     int userID = DatabaseConnector.loginUser(username,password);
     Session.setUserId(userID);
     }
 }
  public void onBack() {
   try {
    Stage stage = (Stage) usernameField.getScene().getWindow();
    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/login.fxml")));
    stage.setScene(scene);
   } catch (IOException e) {
    e.printStackTrace();
   }
  }
}
