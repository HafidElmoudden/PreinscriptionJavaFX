package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import application.Navigation;
import application.database.dbClient;
import application.entities.AccountType;
import application.entities.StudentInformations;
import application.repositories.SignUpRepository;

public class AuthentificationService {

	static public AccountType isLoginValid(String email, String password) {
		String query = "SELECT user_type FROM Users WHERE email = ? AND password_ = ?";
		ResultSet resultSet = dbClient.executeCommand(true, query, List.of(email, password));

		try {
			if (resultSet.next()) {
				Navigation.email = email;
				Navigation.password = password;
				return getAccountType(resultSet.getString(1));
			} else {
				return AccountType.NotFound;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return AccountType.NotFound;
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
	static public void signUp(StudentInformations student)
    {
        SignUpRepository.addUser(student.getEmail(), student.getPassword(), "student");
        SignUpRepository.addEtudiant(student.getCne(), student.getLastName(), student.getFirstName(), student.getCity(), student.getTelephone(), student.getDateNaissance(), student.getEmail(), student.getSexe());
    }
}
