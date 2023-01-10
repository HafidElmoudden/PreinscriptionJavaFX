package application.controllers;


import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import application.Navigation;
import application.entities.AccountType;
import application.entities.StudentInformations;
import application.services.AuthentificationService;
import application.services.CommonService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class AuthentificationController implements Initializable{
	
	@FXML
    private Button suivantbtn1, suivantbtn2, suivantbtn3;
    
    @FXML
    private Button starting_signup, backtologin1, backtologin2, backtologin3;
    
    @FXML
    private Pane login_forum, signup1, signup2, signup3;

    @FXML
    private ChoiceBox<String> bactype_choice, bacville_choice, bacyear_choice, studentcity_choice;

    @FXML
    private DatePicker signup_birthday;

    @FXML
    private TextField signup_cne, signup_confirm_password, signup_lastname, signup_name, signup_telephon, signup_password, sign_email;

    @FXML
    private RadioButton signup_female, signup_male;
    
    
    
    
    

    @FXML
    private Button filladmin;

    @FXML
    private Button fillschool;

    @FXML
    private Button fillstudent;
    
    
    @FXML
    void developmentMode(ActionEvent event) {
    	if (event.getSource() == filladmin) {
			
    		email_login.setText("admin");
    		password_login.setText("admin");
    		handleLogin();
		} else if (event.getSource() == fillschool) {
			
			email_login.setText("esta@ac.uiz.ma");
    		password_login.setText("esta12agadir");
			handleLogin();
		} else if (event.getSource() == fillstudent) {
			
			email_login.setText("mohamedjada@gmail.com");
    		password_login.setText("a");
			handleLogin();
		}
    }
    
    
    
    
    @FXML
    private TextField email_login;

    @FXML
    private Button login_btn;

    @FXML
    private PasswordField password_login;

    public void handleLogin() {
    	Stage stage = (Stage) login_btn.getScene().getWindow();

        String email = email_login.getText();
        String password = password_login.getText();
        AccountType userType = AuthentificationService.isLoginValid(email, password);
        if (userType != AccountType.NotFound) {
        	Navigation navigation = new Navigation();
        	navigation.toAfterLogin(stage,userType);
        	
            // Login successful
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Login Successful : " + userType);
            alert.setHeaderText("Welcome to the app!");
            alert.showAndWait();
            // Redirect the user to another page, or perform some other action
        } else {
            // Login failed
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Invalid email or password");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
    }
    
    
    @FXML
    void starting_signup(ActionEvent event) {
    	signup1.toFront();
    	CommonService.fillBacs(bactype_choice, false);
		CommonService.fillVilles(bacville_choice, false);
		CommonService.fillVilles(studentcity_choice, false);
		bacyear_choice.getItems().addAll("2022", "2021", "2020");
    	
    	
    }
    
    
    StudentInformations signedstudent= new StudentInformations();
    ToggleGroup toggleGroup = new ToggleGroup();
    @FXML
    void suivantbtn1(ActionEvent event) {
    	if(signup_cne.getText().length()==0 || signup_lastname.getText().length()==0 || signup_name.getText().length()==0
    			|| studentcity_choice.getSelectionModel().isEmpty() || signup_birthday.getValue() == null
    			|| !signup_female.isSelected() && !signup_male.isSelected()) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Veuillez d'abord remplir les conditions");
    		alert.showAndWait();
    	}
    	else
    	{
    		LocalDate selectedDate =  signup_birthday.getValue();
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    		String formattedDate = selectedDate.format(formatter);
    		signedstudent.setDateNaissance(formattedDate);
    		signedstudent.setCity(studentcity_choice.getValue());
    		signedstudent.setCne(signup_cne.getText());
    		signedstudent.setFirstName(signup_name.getText());
    		signedstudent.setLastName(signup_lastname.getText());
    		if(((Labeled) toggleGroup.getSelectedToggle()).getText().equals("Fémenin"))
    			signedstudent.setSexe("f");
    		else if(((Labeled) toggleGroup.getSelectedToggle()).getText().equals("Masculin"))
    			signedstudent.setSexe("m");
    		
    	signup2.toFront();
    	}
    }
    @FXML
    void suivantbtn2(ActionEvent event) {
    	if(bactype_choice.getSelectionModel().isEmpty() || bacville_choice.getSelectionModel().isEmpty() || bacyear_choice.getSelectionModel().isEmpty()) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Veuillez d'abord remplir les conditions");
    		alert.showAndWait();
    	}
    	else{
    		signup3.toFront();
    		signedstudent.setBacYear(bacyear_choice.getValue());
    		signedstudent.setBac(bactype_choice.getValue());
    		signedstudent.setBacCity(bacville_choice.getValue());
    		
    		signup3.toFront();
    	}
    }
    @FXML
    void suivantbtn3(ActionEvent event) {
    	if(signup_confirm_password.getText().length()==0 || signup_password.getText().length()==0
    			|| signup_telephon.getText().length()==0 || sign_email.getText().length()==0) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please fill the requirements first");
    		alert.showAndWait();
    	}
    	else if(!signup_confirm_password.getText().equals(signup_password.getText())) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Mots de passe incompatibles");
    		alert.showAndWait();
    	}
    	else {
    		signedstudent.setEmail(sign_email.getText());
    		signedstudent.setPassword(signup_password.getText());
    		signedstudent.setTelephone(signup_telephon.getText());
    		AuthentificationService.signUp(signedstudent);
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setTitle("Opération réussie");
    		alert.setHeaderText(null);
    		alert.setContentText("Se connectez-vous avec vos informations \n"+"Email : "+ signedstudent.getEmail()+"\n Mot de pass :"+ signedstudent.getPassword());
    		alert.showAndWait();
    		login_forum.toFront();
    	}
    	
    }
    
    
    
    
    
    
    
    
    
    @FXML
    void backtologin(ActionEvent event) {
    	login_forum.toFront();
    }
    @FXML
    void handleSexe(ActionEvent event) {
		signup_female.setToggleGroup(toggleGroup);
		signup_male.setToggleGroup(toggleGroup);
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		 
		
		
		
	}

}
