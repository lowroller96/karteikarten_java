package org.example.util;
import org.mindrot.jbcrypt.BCrypt;

import java.net.URL;
import java.sql.*;

import static javax.management.remote.JMXConnectorFactory.connect;

public class DatabaseConnector {
    static String url="jdbc:mysql://localhost:3306/users";
    static String user= "root";
    static String password="";
    {
        try {Connection verbindung = DriverManager.getConnection(url,user,password);
            System.out.println("Verbindung erfolgreich hergestellt!");
        } catch (SQLException ex) {
            System.out.println("Verbindungsabruch");
            throw new RuntimeException(ex);
        }
    }
    public static Connection connect() throws SQLException {
     return DriverManager.getConnection(url,user,password);
    }
    public static boolean registerUser(String username,String password) {
        String sql ="INSERT INTO users (username,password-hash) VALUES (?, ?)";
        try (Connection connection=connect();
             PreparedStatement preparedStatement =connection.prepareStatement(sql)) {
            String hash= BCrypt.hashpw(password, BCrypt.gensalt());
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,hash);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Registrierung fehlgeschlagen! Bitte später nochmalig probieren" + e.getMessage());
            return false;
        }
    }
public static int loginUser(String username,String password) {
        String sql="SELECT id, password_hash FROM users WHERE username = ?";
        try(Connection connection=connect();
        PreparedStatement preparedStatement =connection.prepareStatement(sql){
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String hash = resultSet.getString("password_hash");
                if (BCrypt.checkpw(password,hash)) {
                    return resultSet.getInt("id");
                }
            }
} catch (SQLException e) {
            System.out.println("Login fehlgeschlagen! Bitte später nochmalig versuchen"+e.getMessage());
        }
        return -1;
}


}
