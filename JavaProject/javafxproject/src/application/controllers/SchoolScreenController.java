package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Navigation;
import application.entities.FormationPost;
import application.services.CommonService;
import application.services.SchoolService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SchoolScreenController implements Initializable{

	Navigation navigation = new Navigation();
	@FXML
	private Label identity;
    @FXML
    private TableColumn<FormationPost, String> action_grid_selection, disponible_grid_selection, etablissement_grid_selection, formation_grid_selection, occupee_grid_selection,residuelle_grid_selection ;
    
    @FXML
    private TableColumn<FormationPost, String> cne_grid_etudiants, ville_grid_etudiants, email_grid_etudiants, formation_grid_etudiants, prenom_grid_etudiants, nom_grid_etudiants, statu_grid_etudiants;
    
    @FXML
    private TableColumn<FormationPost, String> vill_grid_candida, email_grid_candida, prenom_grid_candida, nom_grid_candida, cne_grid_candida,  formation_grid_candida;

    @FXML
    private ChoiceBox<String> school_candi_ville_filter, school_formaion_filter, school_etu_formation_filter, school_etu_statu_filter;

    @FXML
    private TableView<FormationPost> shool_grid_etudiants, shcool_grid_candidats, shool_grid_section;

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
    		Navigation navigation = new Navigation();
    		Stage stage = (Stage) logoutbtn.getScene().getWindow();
    		navigation.backToLogin(stage);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		identity.setText(Navigation.email);
		CommonService.fillVilles(school_candi_ville_filter, true);
		
		fillTheCandidatsGrids();
		
		
		
		
	}
    
	
	public void fillTheCandidatsGrids() {
		cne_grid_candida.setCellValueFactory(new PropertyValueFactory<>("student_cne"));
		formation_grid_candida.setCellValueFactory(new PropertyValueFactory<>("formation"));
		vill_grid_candida.setCellValueFactory(new PropertyValueFactory<>("student_ville"));
		nom_grid_candida.setCellValueFactory(new PropertyValueFactory<>("student_nom"));
		prenom_grid_candida.setCellValueFactory(new PropertyValueFactory<>("student_prenom"));
		email_grid_candida.setCellValueFactory(new PropertyValueFactory<>("student_email"));
		
		SchoolService.fillCandidatslist(shcool_grid_candidats,Navigation.email);
	}

}
