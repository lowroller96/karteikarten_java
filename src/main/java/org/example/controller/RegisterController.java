package org.example.controller;
import org.example.util.DatabaseConnector;
import org.example.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.nio.file.*;

public class RegisterController {
 @FXML private TextField usernameField;
 @FXML private PasswordField passwordField;
 @FXML private Label statusLabel;
 @FXML
    public void onRegister() {
     String username=usernameField.getText();
     String password=passwordField.getText();

     if (DatabaseConnector.registerUser(username,password)){
     int userID = DatabaseConnector.loginUser(username,password);
     Session.setUserId(userID);
     }
 }
}
