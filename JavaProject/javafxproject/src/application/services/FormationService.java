package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import application.entities.FormationPost;
import application.repositories.FormationRepository;
import javafx.scene.control.TableView;


public class FormationService {
	
	static public List<FormationPost> getAllFormationPostsData(String bacType, String city){
		List<FormationPost> formations = new ArrayList<>();
		ResultSet result = FormationRepository.getAllFormationPostsData(bacType, city);
		try {
			while(result.next()) {
				FormationPost formation = new FormationPost();
				formation.setEtablissement(result.getString("ecole_nom"));
				formation.setFormation(result.getString("formation_nom"));
				formation.setVille(result.getString("ville"));
				formation.setNbr_chaises_available(result.getString("nbr_chaises_available"));
				formations.add(formation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return formations;
	}
	
	static public void fillFormationPosts(TableView<FormationPost> table, String city){
		table.getItems().clear();
		List<FormationPost> formations = getAllFormationPostsData(null, city);
		for(FormationPost formation : formations) {
			table.getItems().add(formation);
		}
	}
	
	public static void fillMyAppsGrid(TableView<FormationPost> table, String cne) {
	    table.getItems().clear();
		List<FormationPost> formations = getMyApps(cne);
		for(FormationPost formation : formations) {
			table.getItems().add(formation);
		}
	}
	
	static public List<FormationPost> getMyApps(String cne){
	    List<FormationPost> formations = new ArrayList<>();
	    ResultSet result = FormationRepository.getAllMyApps(cne);
	  
		try {
			while(result.next()) {
				FormationPost formation = new FormationPost();
				formation.setEtablissement(result.getString("ecole_nom"));
				formation.setFormation(result.getString("formation_nom"));
				formation.setVille(result.getString("ville"));
				double num = result.getDouble("candidateur_note");
				DecimalFormat df = new DecimalFormat("#.##");
				String formatted = df.format(num);
				formation.setClassement_note((String) formatted);
				formation.setNbr_chaises_available(result.getString("max_chaises"));
				formation.setCandida_code((result.getString("candidateur_code")));
				formations.add(formation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return formations;
	}
	
}
