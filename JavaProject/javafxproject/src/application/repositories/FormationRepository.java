package application.repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import application.database.dbClient;
import application.entities.FormationPost;

public class FormationRepository {
	static public ResultSet getAllFormationPostsData(String bacType, String city) {
	    String query = "SELECT F.cp_code, E.ecole_nom, F.formation_nom, E.ville, F.nbr_chaises_available FROM Ecole E,Formation_Post F WHERE F.ecole_code = E.ecole_code";
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
}
