package application.controllers;

import java.awt.event.ActionEvent;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AuthentificationController implements Initializable{
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
    @FXML
    private TextField email_login;

    @FXML
    private Button login_btn;

    @FXML
    private PasswordField password_login;

    @FXML
    private Label sign_up;
    
    @FXML
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
