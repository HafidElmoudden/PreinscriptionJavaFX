package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Navigation;
import application.database.dbClient;
import application.entities.SchoolFormationPost;
import javafx.scene.control.Alert;

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
		String query= "SELECT A.date_, F.formation_nom, E.ecole_nom, E.ville, F.formation_code FROM Formation_Post F, Ecole E, Affectations A WHERE F.ecole_code = E.ecole_code AND F.cp_code = A.cp_code AND A.reponse = 'En attendant' AND A.cne = ?";
		List<Object> params = new ArrayList<>();
		params.add(cne);
		return dbClient.executeCommand(true, query , params);
	}
    static public ResultSet getSchoolFormationsPosts(String email)
    {
        return dbClient.executeCommand(true, "SELECT F.* FROM Formation_Post F, Ecole E WHERE E.ecole_code = F.ecole_code AND E.email= ?", List.of(email));
    }
    static public ResultSet getMaxChaisesFormationPost(String formationPostCode) {
    	return dbClient.executeCommand(true, "SELECT max_chaises FROM Formation_Post WHERE cp_code = ?", List.of(formationPostCode));
    }
    static public ResultSet getAvailableChaisesFormationPost(String formationPostCode) {
    	return dbClient.executeCommand(true, "SELECT nbr_chaises_available FROM Formation_Post WHERE cp_code = ?", List.of(formationPostCode));
    }
    static public ResultSet getNumberOccupeFormationPost(String formationPostCode) {
    	return dbClient.executeCommand(true, "SELECT nbr_chaises_reserver FROM Formation_Post WHERE cp_code = ?", List.of(formationPostCode));
    }
    static public ResultSet getEligibleCandidats(String formationPostCode) {
    	return dbClient.executeCommand(true, "SELECT cne FROM Candidats WHERE cp_code = ? ORDER BY candidateur_note DESC", List.of(formationPostCode));
    }
    static public void insertIntoAffectations(String cne, String formationPostCode, String status) {
    	dbClient.executeCommand(false, "INSERT INTO Affectations (cne, cp_code, reponse, date_) VALUES (?, ?, ?, getdate())",List.of(cne, formationPostCode, status));
    }
    static public void updateFormationOccupeNumber(String formationPostCode, String nbrChaisesReserver) {
    	dbClient.executeCommand(false, "UPDATE Formation_Post SET nbr_chaises_reserver = nbr_chaises_reserver + ? WHERE formation_code = ?",List.of(nbrChaisesReserver,formationPostCode));
    }
    static public void updateChaisesAvailableNumber(String formationPostCode, String nbrChaisesAvailable) {
    	dbClient.executeCommand(false, "UPDATE Formation_Post SET nbr_chaises_available = nbr_chaises_available - ? WHERE formation_code = ?",List.of(nbrChaisesAvailable,formationPostCode));
    }
    
    
    
    
    
    
    
    
    
    
    
    static public  ResultSet getFormationCofs(String cp_code,String bac) {
    	List<Object> parameters = new ArrayList<>();
        parameters.add(cp_code);
        parameters.add(bac);
        return dbClient.executeCommand(true, "SELECT cof_math, cof_physic, cof_svt, cof_francais FROM Candidateur_Constraints WHERE cp_code= ? AND baccalaureat= ?", parameters);
    	
    }
    static public void insertIntoApplications(int cp_code, String cne, float can_note) {
    	List<Object> parameters = new ArrayList<>();
    	System.out.println("cen from insert "+ cne);
    	parameters.add(cne);
    	int aa = 0, bb = 0;
        // Check if the student has an accepted affectation
    	ResultSet reader = dbClient.executeCommand(true, "SELECT Count(*) as aa FROM Affectations WHERE cne = ? AND reponse = 'Accepté'", parameters);
    	parameters.add(cp_code);
    	ResultSet reader1= dbClient.executeCommand(true, "SELECT  Count(C.cp_code) as bb FROM Candidats C where cne = ? and cp_code = ?", parameters);
        try {
			if (reader.next())
			{
				aa= reader.getInt("aa");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        try {
			if (reader1.next())
			{
				bb= reader1.getInt("bb");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        if(aa!=0) {
        	// If the student has an accepted affectation, do not insert a new record into the Candidats table
        	Alert alert = new Alert(Alert.AlertType.NONE);
			alert.setAlertType(Alert.AlertType.ERROR);
			alert.setContentText("Student already has an accepted affectation and cannot apply to another school.");
			alert.show();
		    return;
        }
        else if(bb!=0){
        	// Si l'etudiant est déja applique pur cette formation
        	Alert alert = new Alert(Alert.AlertType.NONE);
			alert.setAlertType(Alert.AlertType.ERROR);
			alert.setContentText("Vous avez déja appliqué pour cette formation");
			alert.show();
		    return;
        }
        // If the student does not have an accepted affectation, insert a new record into the Candidats table
        List<Object> parameters1 = new ArrayList<>();
        parameters1.add(cp_code);
        parameters1.add(cne);
        parameters1.add(can_note);
        dbClient.executeCommand(false, "INSERT INTO Candidats Values (?,?,?)", parameters1);
        Alert alert = new Alert(Alert.AlertType.NONE);
		alert.setAlertType(Alert.AlertType.INFORMATION);
		alert.setContentText("Applied succesefully to Formation !");
		alert.show();
    }
}
