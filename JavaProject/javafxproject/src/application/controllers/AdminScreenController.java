package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.GlobalControllers;
import application.Navigation;
import application.entities.SchoolInformations;
import application.entities.StudentInformations;
import application.services.CommonService;
import application.services.SchoolService;
import application.services.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminScreenController implements Initializable {
	Navigation navigation = new Navigation();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	@FXML

	private ChoiceBox<String> admin_students_ville_filter;
	@FXML
	private ChoiceBox<String> admin_school_ville_filter;
	@FXML
	private ChoiceBox<String> admin_type_bac_filter;

	@FXML
	private Pane admin_school_list, admin_students_list;

	// admin school list columns
	@FXML
	private TableColumn<SchoolInformations, String> etaCol;
	@FXML
	private TableColumn<SchoolInformations, String> villeCol;
	@FXML
	private TableColumn<SchoolInformations, String> emailCol;
	@FXML
	private TableColumn<SchoolInformations, String> phoneCol;
	@FXML
	private TableColumn<SchoolInformations, String> nbFormationsCol;
	@FXML
	private TableColumn<SchoolInformations, Button> ecoleDeleteCol;
	@FXML
	private TableColumn<SchoolInformations, Button> ecoleEditCol;

	//Admin student list columns
	@FXML
	private TableColumn<StudentInformations, String> cneCol;
	@FXML
	private TableColumn<StudentInformations, String> nomCol;
	@FXML
	private TableColumn<StudentInformations, String> prenomCol;
	@FXML
	private TableColumn<StudentInformations, String> stEmailCol;
	@FXML
	private TableColumn<StudentInformations, String> bacCol;
	@FXML
	private TableColumn<StudentInformations, String> stVilleCol;
	@FXML
	private TableColumn<StudentInformations, Button> deleteCol;
	@FXML
	private TableColumn<StudentInformations, Button> editCol;

	@FXML
	private Button admin_students_listbtn, admin_schools_listbtn, admin_logout;
	//TableView
	@FXML
	private TableView<SchoolInformations> schools_table_view;
	@FXML
	private TableView<StudentInformations> students_table_view;
	
	@FXML
	private TextField ecolesSearchInput;
	@FXML
	private TextField studentsSearchInput;
	
	@FXML
	private Label adminSessionEmail;
	
	@FXML
	void handleButtonAction(ActionEvent event) {

		if (event.getSource() == admin_schools_listbtn) {
			admin_school_list.toFront();
		} else if (event.getSource() == admin_students_listbtn) {
			admin_students_list.toFront();
		} else if (event.getSource() == admin_logout) {
			Stage stage = (Stage) admin_logout.getScene().getWindow();
			navigation.backToLogin(stage);
		}
	}

	public void updateTableViews() {
		String selectedItem = admin_school_ville_filter.getSelectionModel().getSelectedItem();
		SchoolService.fillSchoolsList(schools_table_view, selectedItem, ecolesSearchInput.getText());
		
		String bacSelectedItem = admin_type_bac_filter.getSelectionModel().getSelectedItem();
		String villeSelectedItem = admin_students_ville_filter.getSelectionModel().getSelectedItem();
		StudentService.fillStudentsList(students_table_view, villeSelectedItem, bacSelectedItem, studentsSearchInput.getText());
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		GlobalControllers.adminController = this;
		adminSessionEmail.setText(Navigation.email);
		//School list
		etaCol.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
		villeCol.setCellValueFactory(new PropertyValueFactory<>("ville"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
		nbFormationsCol.setCellValueFactory(new PropertyValueFactory<>("nombreFormations"));
		ecoleDeleteCol.setCellValueFactory(new PropertyValueFactory<>("deleteUser"));
		ecoleEditCol.setCellValueFactory(new PropertyValueFactory<>("editUser"));
		
		//Student list
		cneCol.setCellValueFactory(new PropertyValueFactory<>("cne"));
		nomCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		stEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		bacCol.setCellValueFactory(new PropertyValueFactory<>("bac"));
		stVilleCol.setCellValueFactory(new PropertyValueFactory<>("city"));
		editCol.setCellValueFactory(new PropertyValueFactory<>("editUser"));
		deleteCol.setCellValueFactory(new PropertyValueFactory<>("deleteUser"));
		
		// Search
		ecolesSearchInput.setOnAction(e -> {
			String selectedItem = admin_school_ville_filter.getSelectionModel().getSelectedItem();
			SchoolService.fillSchoolsList(schools_table_view, selectedItem, ecolesSearchInput.getText());			
		});
		studentsSearchInput.setOnAction(e -> {
			String bacSelectedItem = admin_type_bac_filter.getSelectionModel().getSelectedItem();
			String villeSelectedItem = admin_students_ville_filter.getSelectionModel().getSelectedItem();
			StudentService.fillStudentsList(students_table_view, villeSelectedItem, bacSelectedItem, studentsSearchInput.getText());
		});
		
		
		
		admin_school_ville_filter.setOnAction(event -> {
			String selectedItem = admin_school_ville_filter.getSelectionModel().getSelectedItem();
			SchoolService.fillSchoolsList(schools_table_view, selectedItem, ecolesSearchInput.getText());
		});
		admin_students_ville_filter.setOnAction(event -> {
			String bacSelectedItem = admin_type_bac_filter.getSelectionModel().getSelectedItem();
			String villeSelectedItem = admin_students_ville_filter.getSelectionModel().getSelectedItem();
			StudentService.fillStudentsList(students_table_view, villeSelectedItem, bacSelectedItem, studentsSearchInput.getText());
		});
		admin_type_bac_filter.setOnAction(event -> {
			String bacSelectedItem = admin_type_bac_filter.getSelectionModel().getSelectedItem();
			String villeSelectedItem = admin_students_ville_filter.getSelectionModel().getSelectedItem();
			StudentService.fillStudentsList(students_table_view, villeSelectedItem, bacSelectedItem, studentsSearchInput.getText());
		});

		CommonService.fillVilles(admin_school_ville_filter, true);
		admin_school_ville_filter.getSelectionModel().select(0);
		CommonService.fillVilles(admin_students_ville_filter, true);
		admin_students_ville_filter.getSelectionModel().select(0);
		CommonService.fillBacs(admin_type_bac_filter, true);
		admin_type_bac_filter.getSelectionModel().select(0);
		SchoolService.fillSchoolsList(schools_table_view, admin_school_ville_filter.getValue(), null);
		StudentService.fillStudentsList(students_table_view, null, null, null);
	}
}
