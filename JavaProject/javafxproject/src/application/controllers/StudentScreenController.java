package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class StudentScreenController{

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
    		
    		System.out.println("Log out !");
    	}
    }

}
