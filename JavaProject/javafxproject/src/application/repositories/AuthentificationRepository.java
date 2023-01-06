package application.repositories;

import java.sql.ResultSet;

import application.database.dbClient;

public class AuthentificationRepository {
    public static ResultSet getAllUsers() {
        String query = "SELECT * FROM Users";
        return dbClient.executeCommand(true, query, null);
    }
}
