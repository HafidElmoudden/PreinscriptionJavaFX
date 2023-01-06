package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminScreenController {
	Navigation navigation = new Navigation();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane admin_school_list, admin_students_list;

    @FXML
    private Button admin_students_listbtn, admin_schools_listbtn, admin_logout;

    @FXML
    void handleButtonAction(ActionEvent event) {
    	Stage stage = (Stage) admin_logout.getScene().getWindow();
    	if(event.getSource()==admin_schools_listbtn) {
    		
    		admin_school_list.toFront();
    	}
    	else if(event.getSource()==admin_students_listbtn) {
    		
    		admin_students_list.toFront();
    	}
    	else if(event.getSource()==admin_logout) {
    		navigation.backToLogin(stage);
    		System.out.println("Logout");
    		
    	}
    }
}
