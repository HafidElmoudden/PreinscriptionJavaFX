package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import application.entities.BacInformations;
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
				student.setBacInformations(new BacInformations(result.getString("note_math"), result.getString("note_physic"), result.getString("note_svt"), result.getString("note_francais"), result.getString("note_anglais")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	public static void fillStudentsList(TableView<StudentInformations> table, String city, String bac) {
		table.getItems().clear();
		ResultSet rs = StudentRepository.getAllStudentsInformations(city, bac);
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
