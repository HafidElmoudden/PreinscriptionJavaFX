package application.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import application.Navigation;
import application.entities.StudentInformations;
import application.services.StudentService;
import application.utilities.DateParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StudentScreenController implements Initializable{

	Navigation navigation = new Navigation();
	@FXML
	private Label fullname_toshow,email_toshow,cne_toshow,bactype_toshow,age_toshow,bacyear_toshow,city_toshow;
	
    @FXML
    private Pane student_applications, student_notifications, student_infos, student_home;

    @FXML
    private Button student_appsbtn, student_homebtn, student_infosbtn, student_logout, student_notifsbtn ; 

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
	}

}
