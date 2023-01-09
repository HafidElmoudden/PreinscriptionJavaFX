package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import application.entities.StudentInformations;
import application.repositories.StudentRepository;
import javafx.scene.control.TableView;

public class StudentService {
	public static StudentInformations getStudentInformations(String email) {
		StudentInformations student = new StudentInformations();
		ResultSet result = StudentRepository.getStudentInformations(email);
		try {
			while(result.next()) {
				student.setCne(result.getString("cne"));
				student.setLastName(result.getString("nom"));
				student.setFirstName(result.getString("prenom"));
				student.setCity(result.getString("ville"));
				student.setTelephone(result.getString("telephone"));
				student.setDateNaissance(result.getString("date_naissance"));
				student.setEmail(result.getString("email"));
				student.setSexe(result.getString("sexe"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		result = StudentRepository.getStudentBacInformations(student.getCne());
		try {
			while(result.next()) {
				student.setBac(result.getString("baccalaureat"));
				student.setBacYear(result.getString("bac_annee"));
				student.getBacInformations().cne = result.getString(1);
				student.getBacInformations().baccalaureat = result.getString(2);
				student.getBacInformations().bac_anne = result.getString(3);
				student.getBacInformations().note_math = result.getString(4);
				student.getBacInformations().note_physic = result.getString(5);
				student.getBacInformations().note_svt = result.getString(6);
				student.getBacInformations().note_francais = result.getString(7);
				student.getBacInformations().note_anglais = result.getString(8);
				student.getBacInformations().note_bac = result.getString(9);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	public static void fillStudentsList(TableView<StudentInformations> table, String city, String bac, String searchTerm) {
		table.getItems().clear();
		ResultSet rs = StudentRepository.getAllStudentsInformations(city, bac, searchTerm);
		try {
			while (rs.next()) {
				String cne = rs.getString("cne");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String bacType = rs.getString("baccalaureat");
				String ville = rs.getString("ville");
				
				StudentInformations student = new StudentInformations();
				student.setCne(cne);
				student.setFirstName( prenom);
				student.setLastName(nom);
				student.setEmail(email);
				student.setBac( bacType);
				student.setCity( ville);
				
				table.getItems().add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
