package application.repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import application.database.dbClient;
import application.entities.SchoolFormationPost;

public class FormationRepository {
	static public ResultSet getAllFormationPostsData(String bacType, String city) {
	    String query = "SELECT F.cp_code, E.ecole_nom,E.ecole_code, F.formation_nom, E.ville, F.nbr_chaises_available FROM Ecole E,Formation_Post F WHERE F.ecole_code = E.ecole_code";
	    List<Object> params = new ArrayList<>();
	    if ((city == null || city.equals("Toutes les villes")) && (bacType == null || bacType.equals("All"))) {
	        // Do nothing
	    } else if (city == null || city.equals("Toutes les villes")) {
	        query += " AND CC.baccalaureat = ?";
	        params.add(bacType);
	    } else if (bacType == null || bacType.equals("All")) {
	        query += " AND E.ville = ?";
	        params.add(city);
	    } else {
	        query += " AND E.ville = ? AND CC.baccalaureat = ?";
	        params.add(city);
	        params.add(bacType);
	    }
	    return dbClient.executeCommand(true, query, params);
	}
	
	static public ResultSet getAllMyApps(String cne) {
		String query = "SELECT E.ecole_nom, F.formation_nom, C.candidateur_note, E.ville, F.max_chaises, C.candidateur_code FROM Formation_Post F, Candidats C, Ecole E WHERE C.cp_code = F.cp_code AND E.ecole_code = F.ecole_code AND C.cne = ?";
	    List<Object> params = new ArrayList<>();
	    params.add(cne);
	    return dbClient.executeCommand(true, query, params);
	}
	public static ResultSet getNotifs(String cne) {
		String query= "SELECT F.formation_nom, E.ecole_nom, E.ville, F.formation_code FROM Formation_Post F, Ecole E, Affectations A WHERE F.ecole_code = E.ecole_code AND F.cp_code = A.cp_code AND A.reponse = 'En attendant' AND A.cne = ?";
		List<Object> params = new ArrayList<>();
		params.add(cne);
		return dbClient.executeCommand(true, query , params);
	}
    static public ResultSet getSchoolFormationsPosts(String email)
    {
        return dbClient.executeCommand(true, "SELECT F.* FROM Formation_Post F, Ecole E WHERE E.ecole_code = F.ecole_code AND E.email= ?", List.of(email));
    }
}
