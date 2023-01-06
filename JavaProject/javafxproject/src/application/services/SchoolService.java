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

	public static void fillSchoolsListT(TableView<List<String>> table, String city) {
		ResultSet rs = SchoolRepository.getSchools(city);
		ObservableList<List<String>> data = FXCollections.observableArrayList();
		;

		try {
			table.getColumns().clear();
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				// We are using non property style for making dynamic table
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(
						(Callback) new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j).toString());
							}
						});
				table.getColumns().add(col);
				System.out.println("Column [" + i + "] ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/********************************
		 * Data added to ObservableList *
		 ********************************/
		try {
			while (rs.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rs.getString(i));
				}
				data.add(row);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		table.getItems().addAll(data);

	}

}
