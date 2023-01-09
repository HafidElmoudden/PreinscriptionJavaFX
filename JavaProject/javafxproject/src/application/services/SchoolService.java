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
	public static void fillSchoolsList(TableView<SchoolInformations> table, String city) {
		table.getItems().clear();
		ResultSet rs = SchoolRepository.getSchools(city);
		try {
			while (rs.next()) {

				String etablissement = rs.getString("ecole_nom");
				String ville = rs.getString("ville");
				String email = rs.getString("email");
				String telephone = rs.getString("ecole_telephone");
				String nombreFormations = rs.getString("num_formation_posts");
				table.getItems()
						.add(new SchoolInformations(etablissement, ville, email, telephone, nombreFormations));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void fillCandidatslist(TableView<FormationPost> table, String email) {
		table.getItems().clear();
		List<FormationPost> formations = getcandidats(email);
		for(FormationPost formation : formations) {
			table.getItems().add(formation);
		}
	}
	
	public static List<FormationPost> getcandidats(String email){
		List<FormationPost> formations = new ArrayList<>();
	    ResultSet result = SchoolRepository.getCandidateurs(email);
	  
		try {
			while(result.next()) {
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
		for(SchoolFormationPost formation : formations) {
			table.getItems().add(formation);
		}
	}
	
	public static List<StudentInformations> getSelectedStudentsList(String email, String formation, String status){
		List<StudentInformations> studentsInfos = new ArrayList<StudentInformations>();
		ResultSet result = SchoolRepository.getSelectedStudents(email, formation, status);
		try {
			while(result.next()){
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
	
	public static void fillSelectedStudentslist(TableView<StudentInformations> table, String email, String formation, String status) {
		table.getItems().clear();
		List<StudentInformations> studentsInfos = getSelectedStudentsList(email, formation, status);
		for(StudentInformations studentInfo : studentsInfos) {
			table.getItems().add(studentInfo);
		}
	}
	
}
