package application.controllers;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import application.Navigation;
import application.entities.FormationPost;
import application.entities.StudentInformations;
import application.services.CommonService;
import application.services.FormationService;
import application.services.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
	private TableColumn<FormationPost, String> etablissement_apps, fromation_apps, ville_apps, note_apps, residuelle_apps, candidateur_code, action_apps; 
	
	@FXML
	private TableView<FormationPost> my_applications_grid, student_grid_home;

	@FXML
    private Label student_pc,student_math, student_ang, student_svt, student_fra, student_num, student_email;
	
    @FXML
    private TextField confirm_phone, new_phone;
	
    @FXML
    private ChoiceBox<String> student_schoollist_ville_filter;
    
    @FXML
    private Label age_toshow, email_toshow, bactype_toshow, bacyear_toshow, city_toshow, cne_toshow, fullname_toshow;
    
    @FXML
    private TableColumn<?, ?> etablissement_grid_home,  formation_grid_home, residuelle_grid_home, ville_grid_home, viewschool_grid_home, apply_grid_home;

    @FXML
    private Pane student_applications, student_home, student_infos, student_notifications;

    @FXML
    private Button student_appsbtn, student_homebtn, student_infosbtn, student_logout, student_notifsbtn;
    
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
		bacyear_toshow.setText(student.getBacYear());
		city_toshow.setText(student.getCity());
		
		//Fill DropDown
		CommonService.fillVilles(student_schoollist_ville_filter);
		//Detect Filter 
		student_schoollist_ville_filter.setOnAction(event -> {
			String villeSelectedItem = (String) student_schoollist_ville_filter.getSelectionModel().getSelectedItem();
			FormationService.fillFormationPosts(student_grid_home, villeSelectedItem);
		});
		
		//Fill Grid
		fillFormations();
		
		
		//My informations
		student_pc.setText(student.getBacInformations().note_physic);
		student_math.setText(student.getBacInformations().note_math);
		student_ang.setText(student.getBacInformations().note_anglais);
		student_fra.setText(student.getBacInformations().note_francais);
		student_svt.setText(student.getBacInformations().note_svt);
		student_num.setText(student.getTelephone());
		student_email.setText(student.getEmail());
		
		
		//My applications
		fillOrUpdateMyApps((String)student.getCne());

		
		
		
		
		
		
	}
	
	public void fillOrUpdateMyApps(String cne) {
		etablissement_apps.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
		fromation_apps.setCellValueFactory(new PropertyValueFactory<>("formation"));
		ville_apps.setCellValueFactory(new PropertyValueFactory<>("ville"));
		note_apps.setCellValueFactory(new PropertyValueFactory<>("classement_note"));
		residuelle_apps.setCellValueFactory(new PropertyValueFactory<>("nbr_chaises_available"));
		candidateur_code.setCellValueFactory(new PropertyValueFactory<>("candida_code"));
		
		FormationService.fillMyAppsGrid(my_applications_grid, cne);
	}
	
	private void fillFormations() {
		etablissement_grid_home.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
		formation_grid_home.setCellValueFactory(new PropertyValueFactory<>("formation"));
		ville_grid_home.setCellValueFactory(new PropertyValueFactory<>("ville"));
		residuelle_grid_home.setCellValueFactory(new PropertyValueFactory<>("nbr_chaises_available"));

		FormationService.fillFormationPosts(student_grid_home, null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@FXML
    void change_num(ActionEvent event) {
		if(new_phone.getText().length()==0 || confirm_phone.getText().length()==0) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setTitle("Information");
		    alert.setHeaderText("Message");
		    alert.setContentText("Please fill the requerement first");
		    alert.showAndWait();
		}
    }
	
}
