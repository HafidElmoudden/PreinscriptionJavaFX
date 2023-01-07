package application.controllers;

import application.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class SchoolScreenController {

	//shool_grid_section's columns
    @FXML
    private TableColumn<?, ?> action_grid_selection, disponible_grid_selection,
    etablissement_grid_selection, formation_grid_selection,
    occupee_grid_selection,residuelle_grid_selection ;
    
    //school_grid_etudiants's columns
    @FXML
    private TableColumn<?, ?> cne_grid_etudiants, ville_grid_etudiants,
    email_grid_etudiants, formation_grid_etudiants,
    prenom_grid_etudiants, nom_grid_etudiants, statu_grid_etudiants;
    
    //school_grid_home's columns (candidateurs)
    @FXML
    private TableColumn<?, ?> cne_grid_home, email_grid_home,
    formation_grid_home, nom_grid_home, prenom_grid_home,  vill_grid_home;

    //Fillters
    @FXML
    private ChoiceBox<?> school_candi_ville_filter, school_formaion_filter,
    school_etu_formation_filter, school_etu_statu_filter;

    //Table View
    @FXML
    private TableView<?> shool_grid_etudiants, shool_grid_home, shool_grid_section;

    //Navigation btns
    @FXML
    private Button logoutbtn, school_candidateurbtn,
    school_selectionbtn, school_studentbtn;
    
    //Conatiners
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
    		Navigation navigation = new Navigation();
    		Stage stage = (Stage) logoutbtn.getScene().getWindow();
    		navigation.backToLogin(stage);
    	}
    }

}
