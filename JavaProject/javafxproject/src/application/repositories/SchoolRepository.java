package application.repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import application.database.dbClient;

public class SchoolRepository {
	public static ResultSet getSchools(String ville) {
		if (ville == null || ville == "Toutes les villes")
        {
            return dbClient.executeCommand(true, "SELECT E.ecole_nom, E.ville, E.email, E.ecole_telephone,\r\n    (SELECT COUNT(cp_code) FROM Formation_Post WHERE ecole_code = E.ecole_code) AS num_formation_posts\r\nFROM Ecole E", null);
        }
        else
        {
            return dbClient.executeCommand(true, "SELECT E.ecole_nom, E.ville, E.email, E.ecole_telephone,\r\n    (SELECT COUNT(cp_code) FROM Formation_Post WHERE ecole_code = E.ecole_code) AS num_formation_posts\r\nFROM Ecole E WHERE E.ville = ?", List.of(ville));
        }
		
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
}

