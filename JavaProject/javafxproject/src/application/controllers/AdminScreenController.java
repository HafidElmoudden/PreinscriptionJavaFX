package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class AdminScreenController {

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
    	if(event.getSource()==admin_schools_listbtn) {
    		System.out.println("Schools");
    		admin_school_list.toFront();
    	}
    	else if(event.getSource()==admin_students_listbtn) {
    		System.out.println("Students");
    		admin_students_list.toFront();
    	}
    	else if(event.getSource()==admin_logout) {
    		System.out.println("Logout");
    	}
    }
}
