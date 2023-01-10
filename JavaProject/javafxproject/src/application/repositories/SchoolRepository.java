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
	
	public static ResultSet getCandidateurs(String email, String ville, String formation) {
		String query = "SELECT F.formation_nom, C.cne, E.nom, E.prenom, E.email, E.ville FROM Ecole EC, Candidats C, Etudiant E, EtudiantBac EB, Formation_Post F\r\nWHERE C.cne = E.cne AND EB.cne = C.cne AND C.cp_code = F.cp_code AND EC.ecole_code = F.ecole_code AND EC.email = ? ";
		List<Object> parameters = new ArrayList<>();
		parameters.add(email);
		
		if(!ville.equals("Toutes les villes")) {
			query+=" AND E.ville= ?";
			parameters.add(ville);
		}
		if(!formation.equals("Toutes les formations")) {
			query+=" AND F.formation_nom = ?";
			parameters.add(formation);
		}
		
		return dbClient.executeCommand(true, query, parameters);
	}
	
	public static ResultSet getSelectedStudents(String email, String formation, String status) {
		List<Object> parameters = new ArrayList<Object>();
        parameters.add(email);
		String  query = "SELECT DISTINCT F.formation_nom, C.cne, E1.nom, E1.prenom, E1.email, E2.ville, A.reponse FROM Formation_Post F, Ecole E2, Affectations A, Candidats C, Etudiant E1 WHERE F.ecole_code = E2.ecole_code AND F.cp_code = A.cp_code AND A.cne = C.cne AND C.cne = E1.cne AND E2.email = ?";
		if (!formation.equals("Toutes les formations"))
        {
            query += " AND F.formation_nom= ? ";
            parameters.add(formation);
        }
        if (!status.equals("Toutes les réponses"))
        {
            query += " AND A.reponse= ? ";
            parameters.add(status);
        }
		return dbClient.executeCommand(true, query, parameters);

	}
	
	
	
	
	
	public static ResultSet getSchoolInfos(String ecole_code) {
		List<Object> parameters = new ArrayList<Object>();
        parameters.add(ecole_code);
        String query="SELECT ecole_nom, fax, website, description,bg_img_url, logo_url, ecole_adress, ecole_telephone, email FROM Ecole WHERE ecole_code= ?";
        
        return dbClient.executeCommand(true, query, parameters);
	}
	public static ResultSet getFormationByEcoleCode(String ecole_code) {
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(ecole_code);
		String query="SELECT DISTINCT formation_nom FROM Formation_Post WHERE ecole_code= ?";
    
		return dbClient.executeCommand(true, query, parameters);
	}
	public static ResultSet getEcoleCodeByEmail(String email) {
	    String selectEcoleCodeCommand = "SELECT ecole_code FROM Ecole WHERE email = ?";
	    List<Object> selectEcoleCodeParameters = List.of(email);
	    return dbClient.executeCommand(true, selectEcoleCodeCommand, selectEcoleCodeParameters);
	}
	public static void deleteSchool(String ecole_code, String email) {
		String deleteAffectationsCommand = "DELETE FROM Affectations WHERE cp_code IN (SELECT cp_code FROM formation_post WHERE ecole_code = ?)";
		dbClient.executeCommand(false, deleteAffectationsCommand, List.of(ecole_code));

	    String deleteCandidatsCommand = "DELETE FROM Candidats WHERE cp_code IN (SELECT cp_code FROM formation_post WHERE ecole_code = ?)";
	    dbClient.executeCommand(false, deleteCandidatsCommand, List.of(ecole_code));

	    String deleteCandidatConstraintsCommand = "DELETE FROM Candidateur_Constraints WHERE cp_code IN (SELECT cp_code FROM formation_post WHERE ecole_code = ?)";
	    dbClient.executeCommand(false, deleteCandidatConstraintsCommand, List.of(ecole_code));
	    
	    String deleteFormationPostsCommand = "DELETE FROM Formation_Post WHERE ecole_code = ?";
	    dbClient.executeCommand(false, deleteFormationPostsCommand, List.of(ecole_code));
	    
	    String deleteEcoleCommand = "DELETE FROM Ecole WHERE ecole_code = ?";
	    dbClient.executeCommand(false, deleteEcoleCommand, List.of(ecole_code));
	    
	    String deleteUserCommand = "DELETE FROM Users WHERE email = ?";
	    dbClient.executeCommand(false, deleteUserCommand, List.of(email));
	}
	
	public static void updateSchool(String ecole_code,String oldEmail, String etablissement, String ville, String email, String telephone) {
	    String updateEcoleCommand = "UPDATE Ecole SET ecole_nom = ?, ville = ?, email = NULL, ecole_telephone = ? WHERE ecole_code = ?";
	    dbClient.executeCommand(false, updateEcoleCommand, List.of(etablissement, ville, telephone, ecole_code));
	    
	    String updateUserCommand = "UPDATE Users SET email = ? WHERE email = ?";
	    dbClient.executeCommand(false, updateUserCommand, List.of(email, oldEmail));
	    
	    String updateEcoleEmailCommand = "UPDATE Ecole SET email = ? WHERE ecole_code = ?";
	    dbClient.executeCommand(false, updateEcoleEmailCommand, List.of(email, ecole_code));
	}
	
}

