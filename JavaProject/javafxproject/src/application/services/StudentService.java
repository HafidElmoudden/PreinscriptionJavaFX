package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.entities.StudentInformations;
import application.repositories.StudentRepository;
import javafx.scene.control.TableView;

public class StudentService {
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
