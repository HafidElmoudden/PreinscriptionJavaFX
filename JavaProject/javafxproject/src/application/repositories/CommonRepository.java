package application.repositories;

import java.sql.ResultSet;

import application.database.dbClient;

public class CommonRepository {
	public static ResultSet getVilles() {
		String query = "SELECT * FROM Villes";
		return dbClient.executeCommand(true, query, null);
	}
	public static ResultSet getBacs(){
		String query = "SELECT * FROM Baccalaureats";
		return dbClient.executeCommand(true, query, null);
	}
}
