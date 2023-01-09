package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import application.database.dbClient;
import application.entities.FormationPost;
import application.entities.SchoolFormationPost;
import application.repositories.FormationRepository;
import application.repositories.SchoolRepository;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;

public class FormationService {

	static public List<FormationPost> getAllFormationPostsData(String bacType, String city) {
		List<FormationPost> formations = new ArrayList<>();
		ResultSet result = FormationRepository.getAllFormationPostsData(bacType, city);
		try {
			while (result.next()) {
				FormationPost formation = new FormationPost();
				formation.setEtablissement(result.getString("ecole_nom"));
				formation.setFormation(result.getString("formation_nom"));
				formation.setVille(result.getString("ville"));
				formation.setNbr_chaises_available(result.getString("nbr_chaises_available"));
				formation.setEcole_code(result.getString("ecole_code"));
				formations.add(formation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return formations;
	}

	static public void fillFormationPosts(TableView<FormationPost> table, String city) {
		table.getItems().clear();
		List<FormationPost> formations = getAllFormationPostsData(null, city);
		for (FormationPost formation : formations) {
			table.getItems().add(formation);
		}
	}

	public static void fillMyAppsGrid(TableView<FormationPost> table, String cne) {
		table.getItems().clear();
		List<FormationPost> formations = getMyApps(cne);
		for (FormationPost formation : formations) {
			table.getItems().add(formation);
		}
	}

	static public List<FormationPost> getMyApps(String cne) {
		List<FormationPost> formations = new ArrayList<>();
		ResultSet result = FormationRepository.getAllMyApps(cne);

		try {
			while (result.next()) {
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

	public static void fillMyNotifsGrid(TableView<FormationPost> table, String cne) {
		table.getItems().clear();
		List<FormationPost> formations = getMyNotifs(cne);
		for (FormationPost formation : formations) {
			table.getItems().add(formation);
		}
	}

	static public List<FormationPost> getMyNotifs(String cne) {
		List<FormationPost> formations = new ArrayList<>();
		ResultSet result = FormationRepository.getNotifs(cne);

		try {
			while (result.next()) {
				FormationPost formation = new FormationPost();
				formation.setEtablissement(result.getString("ecole_nom"));
				formation.setFormation(result.getString("formation_nom"));
				formation.setVille(result.getString("ville"));
				formation.setFormation_code(result.getString("formation_code"));
				formations.add(formation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return formations;
	}

	static public void fillFormationsChoiceBox(ChoiceBox<String> cb, String email) {

		cb.getItems().add("Toutes les formations");
		cb.setValue("Toutes les formations");
		ResultSet result = FormationRepository.getSchoolFormationsPosts(email);
		try {
			while (result.next()) {
				cb.getItems().add(result.getString("formation_nom"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static public List<SchoolFormationPost> getSchoolFormationPosts(String email) {
		List<SchoolFormationPost> formationPosts = new ArrayList<SchoolFormationPost>();
		ResultSet result = FormationRepository.getSchoolFormationsPosts(email);
		try {
			while (result.next()) {
				SchoolFormationPost formationPost = new SchoolFormationPost(result.getString(1), result.getString(2),
						result.getString(3), result.getString(4), result.getString(5), result.getString(6),
						result.getString(7));
				formationPost.setEcole_nom(SchoolService.getSchoolNameByEmail(email));
				formationPosts.add(formationPost);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return formationPosts;
	}

	public static void addCandidatesToAffectation(String formationPostCode) {
		try {
			int chaisesAvailable = 0;
			ResultSet result = FormationRepository.getAvailableChaisesFormationPost(formationPostCode);
			if (result.next()) {
				chaisesAvailable = result.getInt(1);
			}
			result.close();

			List<String> candidates = new ArrayList<>();
			result = FormationRepository.getEligibleCandidats(formationPostCode);
			while (result.next()) {

				candidates.add(result.getString(1));

			}
			result.close();
			int i;
			for (i = 0; i < chaisesAvailable && i < candidates.size(); i++) {
				FormationRepository.insertIntoAffectations(candidates.get(i), formationPostCode, "En attendant");
			}
			
			FormationRepository.updateFormationOccupeNumber(formationPostCode, String.valueOf(i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
