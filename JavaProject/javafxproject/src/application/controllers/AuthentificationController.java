package application.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

}
