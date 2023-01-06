package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import application.database.dbClient;
import application.entities.AccountType;

public class AuthentificationService {

	static public boolean isLoginValid(String email, String password) {
		String query = "SELECT * FROM Users WHERE email = ? AND password_ = ?";
		ResultSet resultSet = dbClient.executeCommand(true, query, List.of(email, password));
		if(resultSet == null)
			return false;
		try {
			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	static public AccountType getAccountType(String userType) {
		switch (userType) {
		case "student":
			return AccountType.Etudiant;
		case "admin":
			return AccountType.Admin;
		case "school":
			return AccountType.Ecole;
		}
		return AccountType.Etudiant;
	}
}
