package application.controllers;

import application.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class SchoolScreenController {
	Navigation navigation = new Navigation();
    @FXML
    private Button logoutbtn, school_candidateurbtn, school_selectionbtn, school_studentbtn;
    
    @FXML
    private Pane school_candidateurs, school_selection, school_etudiants;

    @FXML
    void handleButtonAction(ActionEvent event) {
    	
    	if(event.getSource()==school_candidateurbtn) {
    		school_candidateurs.toFront();
    	}
    	else if(event.getSource()==school_selectionbtn) {
    		school_selection.toFront();
    	}
    	else if(event.getSource()==school_studentbtn) {
    		school_etudiants.toFront();
    	}
    	else if(event.getSource()==logoutbtn) {
    		Stage stage = (Stage) logoutbtn.getScene().getWindow();
    		navigation.backToLogin(stage);
    	}
    }

}
