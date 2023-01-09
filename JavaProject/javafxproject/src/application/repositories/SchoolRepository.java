package application.repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import application.database.dbClient;

public class SchoolRepository {
	/*public static ResultSet getSchools(String ville, String searchTerm) {
		if (ville == null || ville == "Toutes les villes")
        {
            return dbClient.executeCommand(true, "SELECT E.ecole_nom, E.ville, E.email, E.ecole_telephone,\r\n    (SELECT COUNT(cp_code) FROM Formation_Post WHERE ecole_code = E.ecole_code) AS num_formation_posts\r\nFROM Ecole E", null);
        }
        else
        {
            return dbClient.executeCommand(true, "SELECT E.ecole_nom, E.ville, E.email, E.ecole_telephone,\r\n    (SELECT COUNT(cp_code) FROM Formation_Post WHERE ecole_code = E.ecole_code) AS num_formation_posts\r\nFROM Ecole E WHERE E.ville = ?", List.of(ville));
        }
		
	}*/
	
	public static ResultSet getSchools(String ville, String searchTerm) {
	    List<Object> parameters = new ArrayList<>();

	    String query = "SELECT E.ecole_nom, E.ville, E.email, E.ecole_telephone,   (SELECT COUNT(cp_code) FROM Formation_Post WHERE ecole_code = E.ecole_code) AS num_formation_posts FROM Ecole E";

	    if ((searchTerm != null && searchTerm.length() != 0) || (ville != null && !ville.equals("Toutes les villes"))) {
	        query += " WHERE";
	    }
	    if (searchTerm != null && searchTerm.length() != 0) {
	        query += " (E.ecole_nom LIKE ? OR E.email LIKE ?)";
	        parameters.add("%" + searchTerm + "%");
	        parameters.add("%" + searchTerm + "%");
	    }
	    if (ville != null && !ville.equals("Toutes les villes")) {
	        if (searchTerm != null && searchTerm.length() != 0) {
	            query += " AND";
	        }
	        query += " E.ville = ?";
	        parameters.add(ville);
	    }
	    System.out.println("searchterm = "+ searchTerm +" query = "+query);
	    return dbClient.executeCommand(true, query, parameters);
	}


	
	public static ResultSet getAllStudentsInformations(String cpCode, String city, String bacType) {
		List<Object> parameters = new ArrayList<>();
		parameters.add(cpCode);
		return dbClient.executeCommand(true, "SELECT F.formation_nom, C.cne, E.nom, E.prenom, E.ville, E.email From Formation_Post F, Etudiant E, Candidats C where C.cne=E.cne AND C.cp_code=F.cp_code and F.cp_code=?", parameters);
	}

	public static ResultSet getSchoolNameByEmail(String email) {
		List<Object> parameters = new ArrayList<>();
		parameters.add(email);
		return dbClient.executeCommand(true, "SELECT ecole_nom FROM Ecole WHERE email=?", parameters);
	}
	
	public static ResultSet getCandidateurs(String email) {
		String query = "SELECT F.formation_nom, C.cne, E.nom, E.prenom, E.email, E.ville FROM Ecole EC, Candidats C, Etudiant E, EtudiantBac EB, Formation_Post F\r\nWHERE C.cne = E.cne AND EB.cne = C.cne AND C.cp_code = F.cp_code AND EC.ecole_code = F.ecole_code AND EC.email = ? ";
		List<Object> parameters = new ArrayList<>();
		parameters.add(email);
		return dbClient.executeCommand(true, query, parameters);
	}
	
	public static ResultSet getSelectedStudents(String email, String formation, String status) {
		List<Object> parameters = new ArrayList<Object>();
        parameters.add(email);
		String  query = "SELECT DISTINCT F.formation_nom, C.cne, E1.nom, E1.prenom, E1.email, E2.ville, A.reponse FROM Formation_Post F, Ecole E2, Affectations A, Candidats C, Etudiant E1 WHERE F.ecole_code = E2.ecole_code AND F.cp_code = A.cp_code AND A.cne = C.cne AND C.cne = E1.cne AND E2.email = ?";
        System.out.println("email = "+email+" formation = "+formation+" status = "+status);
		if (!formation.equals("Toutes les formations") && formation.length() != 0 && formation != "")
        {
			System.out.println("isnde of formation if happened " + (formation != "Toutes les formations" && formation.length() != 0 && formation != ""));
            query += " AND F.formation_nom= ? ";
            parameters.add(formation);
        }
        if (!status.equals("Toutes les réponses") && status.length() != 0 && status != "")
        {
        	System.out.println("inside of status if happened " + (!status.equals("Toutes les réponses") && status.length() != 0 && status != ""));
            query += " AND A.reponse= ? ";
            parameters.add(status);
        }
		return dbClient.executeCommand(true, query, parameters);

	}
}

