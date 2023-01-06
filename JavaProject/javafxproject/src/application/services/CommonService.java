package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.repositories.CommonRepository;
import javafx.scene.control.ChoiceBox;

public class CommonService {
	public static void fillVilles(ChoiceBox<String> cb) {
		try {
			ResultSet villes = CommonRepository.getVilles();
			while(villes.next()) {
				cb.getItems().add(villes.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void fillBacs(ChoiceBox<String> cb) {
		try {
			ResultSet bacs = CommonRepository.getBacs();
			while(bacs.next()) {
				cb.getItems().add(bacs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}