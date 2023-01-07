package application.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import application.Navigation;
import application.entities.FormationPost;
import application.entities.SchoolInformations;
import application.entities.StudentInformations;
import application.services.CommonService;
import application.services.FormationService;
import application.services.StudentService;
import application.utilities.DateParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StudentScreenController implements Initializable{

	Navigation navigation = new Navigation();
	@FXML
    private TableColumn<?, ?> actions_grid_home;

    @FXML
    private ChoiceBox<?> student_schoollist_ville_filter;
    @FXML
    private Label age_toshow;

    @FXML
    private Label bactype_toshow;

    @FXML
    private Label bacyear_toshow;

    @FXML
    private Label city_toshow;

    @FXML
    private Label cne_toshow;

    @FXML
    private Label email_toshow;

    @FXML
    private TableColumn<?, ?> etablissement_grid_home;

    @FXML
    private TableColumn<?, ?> formation_grid_home;

    @FXML
    private Label fullname_toshow;

    @FXML
    private TableColumn<?, ?> residuelle_grid_home;

    @FXML
    private Pane student_applications;

    @FXML
    private Button student_appsbtn;

    @FXML
    private TableView<?> student_grid_home;

    @FXML
    private Pane student_home;

    @FXML
    private Button student_homebtn;

    @FXML
    private Pane student_infos;

    @FXML
    private Button student_infosbtn;

    @FXML
    private Button student_logout;

    @FXML
    private Pane student_notifications;

    @FXML
    private Button student_notifsbtn;

    @FXML
    private TableColumn<?, ?> ville_grid_home;
    
    @FXML
    void handleButtonAction(ActionEvent event) {
    	
    	if(event.getSource()==student_appsbtn) {
    		student_applications.toFront();
    	}
    	else if(event.getSource()==student_homebtn) {
    		
    		student_home.toFront();
    	}
    	else if(event.getSource()==student_infosbtn) {
    		student_infos.toFront();
    	}
    	else if(event.getSource()==student_notifsbtn) {
    		student_notifications.toFront();
    	}
    	else if(event.getSource()==student_logout) {
    		Stage stage = (Stage) student_logout.getScene().getWindow();
    		navigation.backToLogin(stage);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		StudentInformations student = StudentService.getStudentInformations(Navigation.email);
		fullname_toshow.setText(student.getFirstName() + " " + student.getLastName());
		email_toshow.setText(student.getEmail());
		cne_toshow.setText(student.getCne());
		bactype_toshow.setText(student.getBac());
		
		LocalDate parseDateNai = DateParser.parseDate(student.getDateNaissance());
        Period age = Period.between(parseDateNai, LocalDate.now());
		age_toshow.setText(String.valueOf(age.getYears()));
		
		bacyear_toshow.setText(student.getBacYear());
		city_toshow.setText(student.getCity());
		
		CommonService.fillVilles((ChoiceBox<String>) student_schoollist_ville_filter);
		
		etablissement_grid_home.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
		formation_grid_home.setCellValueFactory(new PropertyValueFactory<>("formation"));
		ville_grid_home.setCellValueFactory(new PropertyValueFactory<>("ville"));
		residuelle_grid_home.setCellValueFactory(new PropertyValueFactory<>("nbr_chaises_available"));
		
		student_schoollist_ville_filter.setOnAction(event -> {
			String villeSelectedItem = (String) student_schoollist_ville_filter.getSelectionModel().getSelectedItem();
			FormationService.fillFormationPosts(student_grid_home, villeSelectedItem);
		});
		FormationService.fillFormationPosts(student_grid_home, null);
		
	}

}
