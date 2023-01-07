package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import application.entities.SchoolInformations;
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
}
