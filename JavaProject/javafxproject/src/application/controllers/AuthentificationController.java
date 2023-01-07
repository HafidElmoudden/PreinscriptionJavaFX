package application.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.Navigation;
import application.entities.AccountType;
import application.services.AuthentificationService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class AuthentificationController{
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
    
    @FXML
    private Button suivantbtn1, suivantbtn2, suivantbtn3;
    
    @FXML
    private Button starting_signup, backtologin1, backtologin2, backtologin3;
    
    @FXML
    private TextField sign_email;
    
    @FXML
    private Pane login_forum, signup1, signup2, signup3;

    @FXML
    private ComboBox<?> signup_baccity, signup_bactype, signup_city, signup_yearbac;

    @FXML
    private DatePicker signup_birthday;

    @FXML
    private TextField signup_cne, signup_confirm_password, signup_lastname, signup_name, signup_telephon, signup_password;

    @FXML
    private RadioButton signup_female, signup_male;
    
    
    
    
    
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
}
