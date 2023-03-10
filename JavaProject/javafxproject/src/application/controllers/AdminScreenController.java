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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminScreenController implements Initializable {
	Navigation navigation = new Navigation();
	
	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private ChoiceBox<String> admin_students_ville_filter, admin_school_ville_filter, admin_type_bac_filter;
	@FXML
	private Pane admin_school_list, admin_students_list;
	@FXML
	private TableView<SchoolInformations> schools_table_view;
	// Admin : school list columns
	@FXML
	private TableColumn<SchoolInformations, String> etaCol, villeCol, emailCol, phoneCol, ecoleEditCol, nbFormationsCol,  ecoleDeleteCol;
	@FXML
	private TableView<StudentInformations> students_table_view;
	// Admin : student list columns
	@FXML
	private TableColumn<StudentInformations, String> cneCol,  stVilleCol,  nomCol,  prenomCol,  stEmailCol,  bacCol;
	@FXML
	private TableColumn<StudentInformations, Button> editCol, deleteCol;
	@FXML
	private Button admin_students_listbtn, admin_schools_listbtn, admin_logout, clear_search, clear_search1;
	@FXML
	private TextField ecolesSearchInput, studentsSearchInput;
	@FXML
	private Label adminSessionEmail;

	@FXML
	void handleButtonAction(ActionEvent event) {

		if (event.getSource() == admin_schools_listbtn) {
			admin_school_list.toFront();
			toggleStyleClass(admin_schools_listbtn);
		} else if (event.getSource() == admin_students_listbtn) {
			admin_students_list.toFront();
			toggleStyleClass(admin_students_listbtn);
		} else if (event.getSource() == admin_logout) {
			Stage stage = (Stage) admin_logout.getScene().getWindow();
			navigation.backToLogin(stage);
		}
	}

	public void toggleStyleClass(@SuppressWarnings("exports") Button event) {
		Image image2 = new Image(getClass().getResourceAsStream("imgs/default.jpg"));
		Image image1 = new Image(getClass().getResourceAsStream("imgs/default.jpg"));
		ImageView imageView2 = new ImageView(image2);
		ImageView imageView1 = new ImageView(image1);
		if (event == admin_schools_listbtn) {
			admin_schools_listbtn.getStyleClass().add("active");
			admin_students_listbtn.getStyleClass().removeAll("active");
			image2 = new Image(getClass().getResourceAsStream("imgs/school_b.png"));
			image1 = new Image(getClass().getResourceAsStream("imgs/student_w.png"));
		} else if (event == admin_students_listbtn) {
			admin_students_listbtn.getStyleClass().add("active");
			admin_schools_listbtn.getStyleClass().removeAll("active");
			image2 = new Image(getClass().getResourceAsStream("imgs/school_w.png"));
			image1 = new Image(getClass().getResourceAsStream("imgs/student_b.png"));
		}
		imageView2 = new ImageView(image2);
		imageView1 = new ImageView(image1);
		imageView1.setFitWidth(24);
		imageView1.setFitHeight(24);
		imageView2.setFitWidth(24);
		imageView2.setFitHeight(24);
		admin_students_listbtn.setGraphic(imageView1);
		admin_schools_listbtn.setGraphic(imageView2);
	}

	@FXML
	void clear_search(ActionEvent event) {
		if (event.getSource() == clear_search) {
			studentsSearchInput.setText("");
			String bacSelectedItem = admin_type_bac_filter.getSelectionModel().getSelectedItem();
			String villeSelectedItem = admin_students_ville_filter.getSelectionModel().getSelectedItem();
			StudentService.fillStudentsList(students_table_view, villeSelectedItem, bacSelectedItem,
					studentsSearchInput.getText());
		} else if (event.getSource() == clear_search1) {
			ecolesSearchInput.setText("");
			String selectedItem = admin_school_ville_filter.getSelectionModel().getSelectedItem();
			SchoolService.fillSchoolsList(schools_table_view, selectedItem, ecolesSearchInput.getText());
		}
		clear_search.setVisible(false);
		clear_search1.setVisible(false);
	}

	public void updateTableViews() {
		String selectedItem = admin_school_ville_filter.getSelectionModel().getSelectedItem();
		SchoolService.fillSchoolsList(schools_table_view, selectedItem, ecolesSearchInput.getText());

		String bacSelectedItem = admin_type_bac_filter.getSelectionModel().getSelectedItem();
		String villeSelectedItem = admin_students_ville_filter.getSelectionModel().getSelectedItem();
		StudentService.fillStudentsList(students_table_view, villeSelectedItem, bacSelectedItem,
				studentsSearchInput.getText());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		toggleStyleClass(admin_schools_listbtn);

		GlobalControllers.adminController = this;
		adminSessionEmail.setText(Navigation.email);
		// School list
		etaCol.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
		villeCol.setCellValueFactory(new PropertyValueFactory<>("ville"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
		nbFormationsCol.setCellValueFactory(new PropertyValueFactory<>("nombreFormations"));
		ecoleDeleteCol.setCellValueFactory(new PropertyValueFactory<>("deleteUser"));
		ecoleEditCol.setCellValueFactory(new PropertyValueFactory<>("editUser"));

		// Student list
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
			clear_search1.setVisible(true);
		});
		studentsSearchInput.setOnAction(e -> {
			String bacSelectedItem = admin_type_bac_filter.getSelectionModel().getSelectedItem();
			String villeSelectedItem = admin_students_ville_filter.getSelectionModel().getSelectedItem();
			StudentService.fillStudentsList(students_table_view, villeSelectedItem, bacSelectedItem,
					studentsSearchInput.getText());
			clear_search.setVisible(true);
		});

		admin_school_ville_filter.setOnAction(event -> {
			String selectedItem = admin_school_ville_filter.getSelectionModel().getSelectedItem();
			SchoolService.fillSchoolsList(schools_table_view, selectedItem, ecolesSearchInput.getText());
		});
		admin_students_ville_filter.setOnAction(event -> {
			String bacSelectedItem = admin_type_bac_filter.getSelectionModel().getSelectedItem();
			String villeSelectedItem = admin_students_ville_filter.getSelectionModel().getSelectedItem();
			StudentService.fillStudentsList(students_table_view, villeSelectedItem, bacSelectedItem,
					studentsSearchInput.getText());
		});
		admin_type_bac_filter.setOnAction(event -> {
			String bacSelectedItem = admin_type_bac_filter.getSelectionModel().getSelectedItem();
			String villeSelectedItem = admin_students_ville_filter.getSelectionModel().getSelectedItem();
			StudentService.fillStudentsList(students_table_view, villeSelectedItem, bacSelectedItem,
					studentsSearchInput.getText());
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
