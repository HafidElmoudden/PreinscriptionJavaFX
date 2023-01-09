package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import application.database.dbClient;
import application.entities.FormationPost;
import application.entities.SchoolFormationPost;
import application.entities.SchoolInformations;
import application.entities.StudentInformations;
import application.repositories.FormationRepository;
import application.repositories.SchoolRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class SchoolService {
	public static void fillSchoolsList(TableView<SchoolInformations> table, String city, String searchTerm) {
		table.getItems().clear();
		ResultSet rs = SchoolRepository.getSchools(city, searchTerm);
		try {
			while (rs.next()) {

				String etablissement = rs.getString("ecole_nom");
				String ville = rs.getString("ville");
				String email = rs.getString("email");
				String telephone = rs.getString("ecole_telephone");
				String nombreFormations = rs.getString("num_formation_posts");
				table.getItems().add(new SchoolInformations(etablissement, ville, email, telephone, nombreFormations));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void fillCandidatslist(TableView<FormationPost> table, String email, String ville,
			String formation_) {
		table.getItems().clear();
		List<FormationPost> formations = getcandidats(email, ville, formation_);
		for (FormationPost formation : formations) {
			table.getItems().add(formation);
		}
	}

	public static List<FormationPost> getcandidats(String email, String ville, String formation_) {
		List<FormationPost> formations = new ArrayList<>();
		ResultSet result = SchoolRepository.getCandidateurs(email, ville, formation_);

		try {
			while (result.next()) {
				FormationPost formation = new FormationPost();

				formation.setFormation(result.getString("formation_nom"));
				formation.setStudentCne(result.getString("cne"));
				formation.setStudentNom(result.getString("nom"));
				formation.setStudentPrenom(result.getString("prenom"));
				formation.setstudentEmail(result.getString("email"));
				formation.setStudentVille(result.getString("ville"));

				formations.add(formation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return formations;
	}

	public static String getSchoolNameByEmail(String email) {
		String schoolName = null;
		ResultSet result = SchoolRepository.getSchoolNameByEmail(email);
		try {
			result.next();
			schoolName = result.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return schoolName;
	}

	public static void fillSelectionlist(TableView<SchoolFormationPost> table, String email) {
		table.getItems().clear();
		List<SchoolFormationPost> formations = FormationService.getSchoolFormationPosts(email);
		for (SchoolFormationPost formation : formations) {
			table.getItems().add(formation);
		}
	}

	public static List<StudentInformations> getSelectedStudentsList(String email, String formation, String status) {
		List<StudentInformations> studentsInfos = new ArrayList<StudentInformations>();
		ResultSet result = SchoolRepository.getSelectedStudents(email, formation, status);
		try {
			while (result.next()) {
				StudentInformations studentInfo = new StudentInformations();
				studentInfo.setFormationNom(result.getString(1));
				studentInfo.setCne(result.getString(2));
				studentInfo.setLastName(result.getString(3));
				studentInfo.setFirstName(result.getString(4));
				studentInfo.setEmail(result.getString(5));
				studentInfo.setCity(result.getString(6));
				studentInfo.setReponse(result.getString(7));

				studentsInfos.add(studentInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentsInfos;
	}

	public static void fillSelectedStudentslist(TableView<StudentInformations> table, String email, String formation,
			String status) {
		table.getItems().clear();
		List<StudentInformations> studentsInfos = getSelectedStudentsList(email, formation, status);
		for (StudentInformations studentInfo : studentsInfos) {
			table.getItems().add(studentInfo);
		}
	}

	public static SchoolInformations getSchoolInformations(String ecole_code) {
		SchoolInformations s = new SchoolInformations();
		ResultSet result = SchoolRepository.getSchoolInfos(ecole_code);
		try {
			while (result.next()) {
				s.setAdress(result.getString("ecole_adress"));
				s.setEtablissement(result.getString("ecole_nom"));
				s.setEmail(result.getString("email"));
				s.setPhone(result.getString("ecole_telephone"));
				s.setDescription(result.getString("description"));
				s.setFax(result.getString("fax"));
				s.setWebsite(result.getString("website"));
				s.setBanner(result.getString("bg_img_url"));
				s.setLogo(result.getString("logo_url"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return s;
	}

	public static void fillFomationView(TableView<FormationPost> table, String ecole_code) {
		table.getItems().clear();
		List<FormationPost> formas = new ArrayList<FormationPost>();
		ResultSet result = SchoolRepository.getFormationByEcoleCode(ecole_code);
		try {
			while (result.next()) {
				FormationPost f = new FormationPost();
				f.setFormation(result.getString(1));

				formas.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (FormationPost forma : formas) {
			table.getItems().add(forma);
		}
	}

	public static String getEcoleCodeByEmail(String email) {
		ResultSet result = SchoolRepository.getEcoleCodeByEmail(email);
		try {
			if (result.next()) {
				return result.getString("ecole_code");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
