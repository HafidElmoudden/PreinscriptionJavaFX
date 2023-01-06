package application.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.Navigation;
import application.repositories.CommonRepository;
import application.services.CommonService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminScreenController implements Initializable{
	Navigation navigation = new Navigation();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    
    private ChoiceBox<String> admin_students_ville_filter;
    @FXML
    private ChoiceBox<String> admin_school_ville_filter;
    @FXML
    private ChoiceBox<String> admin_type_bac_filter;
    
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CommonService.fillVilles(admin_school_ville_filter);
		CommonService.fillVilles(admin_students_ville_filter);
		CommonService.fillBacs(admin_type_bac_filter);
	}
}
