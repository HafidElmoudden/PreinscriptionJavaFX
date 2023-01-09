package application.repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import application.database.dbClient;

public class StudentRepository {
	public static ResultSet getStudentCneByEmail(String email) {
		return dbClient.executeCommand(true, "SELECT cne FROM Etudiant WHERE email = ?", List.of(email));
	}
    
	public static ResultSet getStudentInformations(String email) {
		List<Object> parameters = new ArrayList<>();
		parameters.add(email);
		return dbClient.executeCommand(true, "SELECT * FROM Etudiant WHERE email = ?", parameters);
	}

	public static ResultSet getStudentBacInformations(String cne) {
		List<Object> parameters = new ArrayList<>();
		parameters.add(cne);
		return dbClient.executeCommand(true, "SELECT * FROM EtudiantBac WHERE cne = ?", parameters);
	}

	/*public static ResultSet getAllStudentsInformations(String city, String bacType,String searchTerm) {
		if ((city == null || city.equals("Toutes les villes")) && (bacType == null || bacType.equals("Tous types de BAC"))) {
			return dbClient.executeCommand(true, "SELECT E.cne, E.nom, E.prenom, E.email, EB.baccalaureat, E.ville FROM Etudiant E, EtudiantBac EB WHERE E.cne = EB.cne", null);
		} else {
			if (bacType == null || bacType.equals("Tous types de BAC")) {
				return dbClient.executeCommand(true, "SELECT E.cne, E.nom, E.prenom, E.email, EB.baccalaureat, E.ville FROM Etudiant E, EtudiantBac EB WHERE E.cne = EB.cne AND E.ville = ?", List.of(city));
			}
			if (city == null || city.equals("Toutes les villes")) {
				return dbClient.executeCommand(true, "SELECT E.cne, E.nom, E.prenom, E.email, EB.baccalaureat, E.ville FROM Etudiant E, EtudiantBac EB WHERE E.cne = EB.cne AND EB.baccalaureat = ?", List.of(bacType));
			}
			return dbClient.executeCommand(true, "SELECT E.cne, E.nom, E.prenom, E.email, EB.baccalaureat, E.ville FROM Etudiant E, EtudiantBac EB WHERE E.cne = EB.cne AND E.ville = ? AND EB.baccalaureat = ?", List.of(city, bacType));
		}
	}*/
	public static ResultSet getAllStudentsInformations(String city, String bacType, String searchTerm) {
	    List<Object> parameters = new ArrayList<>();

	    String query = "SELECT E.cne, E.nom, E.prenom, E.email, EB.baccalaureat, E.ville FROM Etudiant E, EtudiantBac EB WHERE E.cne = EB.cne";

	    if (searchTerm != null) {
	        query += " AND (E.nom LIKE ? OR E.prenom LIKE ? OR E.email LIKE ? OR E.cne LIKE ?)";
	        parameters.add("%" + searchTerm + "%");
	        parameters.add("%" + searchTerm + "%");
	        parameters.add("%" + searchTerm + "%");
	        parameters.add("%" + searchTerm + "%");
	    }
	    if ((city == null || city.equals("Toutes les villes")) && (bacType == null || bacType.equals("Tous types de BAC"))) {
	        return dbClient.executeCommand(true, query, parameters);
	    } else {
	        if (bacType == null || bacType.equals("Tous types de BAC")) {
	        	parameters.add(city);
	            return dbClient.executeCommand(true, query + " AND E.ville = ?", parameters);
	        }
	        if (city == null || city.equals("Toutes les villes")) {
	        	parameters.add(bacType);
	            return dbClient.executeCommand(true, query + " AND EB.baccalaureat = ?", parameters);
	        }
	        parameters.add(city);
	        parameters.add(bacType);
	        return dbClient.executeCommand(true, query + " AND E.ville = ? AND EB.baccalaureat = ?", parameters);
	    }
	}
	public static void changeStudentPhone(String cne, String newPhone) {
		List<Object> parameters = new ArrayList<>();
		parameters.add(newPhone);
		parameters.add(cne);
		dbClient.executeCommand(false, "UPDATE Etudiant SET telephone = ? WHERE cne = ?", parameters);
	}
	
	public static void changeStudentEmail(String oldEmail, String newEmail, String cne) {
		List<Object> parameters = new ArrayList<>();
		parameters.add(oldEmail);
		dbClient.executeCommand(false, "UPDATE Etudiant SET email = NULL WHERE email = ?", parameters);
		parameters = new ArrayList<>();
		parameters.add(newEmail);
		parameters.add(oldEmail);
		dbClient.executeCommand(false, "UPDATE Users SET email = ? WHERE email = ?", parameters);
		parameters = new ArrayList<>();
		parameters.add(newEmail);
		parameters.add(cne);
		dbClient.executeCommand(false, "UPDATE Etudiant SET email = ? WHERE cne = ?", parameters);
	}
	
	public static void changeStudentPassword(String email, String newPassword) {
		List<Object> parameters = new ArrayList<>();
		parameters.add(newPassword);
		parameters.add(email);
		dbClient.executeCommand(false, "UPDATE Users SET password_ = ? WHERE email = ?", parameters);
	}

	public static ResultSet getAllMyApps(String cne) {
		List<Object> parameters = new ArrayList<>();
		parameters.add(cne);
		return dbClient.executeCommand(true, "SELECT E.ecole_nom, F.formation_nom, C.candidateur_note,E.ville, F.max_chaises, C.candidateur_code FROM Formation_Post F, Candidats C, Ecole E WHERE C.cp_code = F.cp_code and E.ecole_code = F.ecole_code and C.cne = ?", parameters);
	}
	
}