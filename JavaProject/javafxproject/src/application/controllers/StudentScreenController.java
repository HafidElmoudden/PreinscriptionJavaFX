package application.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import application.Navigation;
import application.entities.StudentInformations;
import application.repositories.StudentRepository;
import application.services.CommonService;
import application.services.FormationService;
import application.services.StudentService;
import application.utilities.DateParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    
    //Student releve de notes
    @FXML
    private Label anglaisNote;
    @FXML
    private Label phyNote;
    @FXML
    private Label svtNote;
    @FXML
    private Label frenchNote;
    @FXML
    private Label mathNote;
    
    //Email fields
    @FXML
    private Label emailActuel;
    @FXML
    private TextField newEmailField;
    @FXML
    private TextField confirmEmailField;
    @FXML
    private Button changeEmailBtn;
    
    //Password fields
    @FXML
    private TextField currPassField;
    @FXML
    private TextField newPassField;
    @FXML
    private TextField confPassField;
    @FXML
    private Button changePassBtn;
    
    //Phone fields
    @FXML
    private TextField newPhoneField;
    @FXML
    private TextField confPhoneField;
    @FXML
    private Button changePhoneBtn;
    
    //My notifications table
    @FXML
    private TableView notifications_grid;
    @FXML
    private TableColumn<?, ?> etablissement_notifs;
    @FXML
    private TableColumn<?, ?> formation_notifs;
    @FXML
    private TableColumn<?, ?> ville_notifs;
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
		emailActuel.setText(Navigation.email);
		StudentInformations student = StudentService.getStudentInformations(Navigation.email);
		//Header informations fill
		fullname_toshow.setText(student.getFirstName() + " " + student.getLastName());
		email_toshow.setText(student.getEmail());
		cne_toshow.setText(student.getCne());
		bactype_toshow.setText(student.getBac());
		LocalDate parseDateNai = DateParser.parseDate(student.getDateNaissance());
		if(parseDateNai != null) {
			Period age = Period.between(parseDateNai, LocalDate.now());
			age_toshow.setText(String.valueOf(age.getYears()));
		}
			
		bacyear_toshow.setText(student.getBacYear());
		city_toshow.setText(student.getCity());
		
		//Notes fill
		anglaisNote.setText(student.getBacInformations().note_anglais);
		phyNote.setText(student.getBacInformations().note_physic);
		svtNote.setText(student.getBacInformations().note_svt);
		frenchNote.setText(student.getBacInformations().note_francais);
		mathNote.setText(student.getBacInformations().note_math);
		
		CommonService.fillVilles((ChoiceBox<String>) student_schoollist_ville_filter);
		//Email Change Action
		changeEmailBtn.setOnAction(e -> {
			Alert alert = new Alert(Alert.AlertType.NONE);
			if(newEmailField.getText().equals(Navigation.email) ) {
				alert.setAlertType(Alert.AlertType.ERROR);
				alert.setContentText("L'email entrée est l'e-mail actuel");
				alert.show();
			}
			else if(newEmailField.getText().equals(confirmEmailField.getText())) {
				StudentRepository.changeStudentEmail(Navigation.email, newEmailField.getText(), student.getCne());
				alert.setAlertType(Alert.AlertType.CONFIRMATION);
				alert.setContentText("l'email a changé avec succès");
				alert.show();
			}
			else {
				alert.setAlertType(Alert.AlertType.ERROR);
				alert.setContentText("Vérifiez les champs e-mail : "+newEmailField.getText()+" , " + confirmEmailField.getText());
				alert.show();
			}
				
		});
		
		//Password change action
		changePassBtn.setOnAction(e -> {
			Alert alert = new Alert(Alert.AlertType.NONE);

			if(currPassField.getText().equals(Navigation.password) && newPassField.getText().equals(confPassField.getText())) {
				StudentRepository.changeStudentPassword(Navigation.email, newPassField.getText());
				alert.setAlertType(Alert.AlertType.CONFIRMATION);
				alert.setContentText("le mote de passe a changé avec succès");
				alert.show();
			} else {
				alert.setAlertType(Alert.AlertType.ERROR);
				alert.setContentText("Vérifiez les champs de mote de passe!");
				alert.show();
			}
		});
		
		//Phone change action
		changePhoneBtn.setOnAction(e -> {
			Alert alert = new Alert(Alert.AlertType.NONE);

			if(newPhoneField.getText().equals(confPhoneField.getText())) {
				StudentRepository.changeStudentPhone(student.getCne(),newPhoneField.getText());
				alert.setAlertType(Alert.AlertType.CONFIRMATION);
				alert.setContentText("le numero de telephone a changé avec succès");
				alert.show();
			} else {
				alert.setAlertType(Alert.AlertType.ERROR);
				alert.setContentText("Vérifiez les champs des numero de telephone!");
				alert.show();
			}
		});
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
	
	
	
	
	private void fillNotifications(String cne) {
		
		etablissement_notifs.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
		formation_notifs.setCellValueFactory(new PropertyValueFactory<>("formation"));
		ville_notifs.setCellValueFactory(new PropertyValueFactory<>("ville"));

		FormationService.fillMyNotifsGrid(notifications_grid, cne);
	}
	
}
