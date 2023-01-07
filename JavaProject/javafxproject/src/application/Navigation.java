package application;

import java.io.IOException;

import application.entities.AccountType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Navigation {
	public static String email = "";
	public static String password = "";
	public void toAfterLogin(Stage stage,AccountType accType) {
		Pane root;
		switch(accType) {
		case Admin:
			try {
				root = FXMLLoader.load(getClass().getResource("AdminScreen.fxml"));
				Scene scene = new Scene(root,1200,720);
				scene.getStylesheets().add(getClass().getResource("Application.css").toExternalForm());
				stage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case Etudiant:
			try {
				root = FXMLLoader.load(getClass().getResource("StudentScreen.fxml"));
				Scene scene = new Scene(root,1200,720);
				scene.getStylesheets().add(getClass().getResource("Application.css").toExternalForm());
				stage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case Ecole:
			try {
				root = FXMLLoader.load(getClass().getResource("SchoolScreen.fxml"));
				Scene scene = new Scene(root,1200,720);
				scene.getStylesheets().add(getClass().getResource("Application.css").toExternalForm());
				stage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		default:
			break;
		}
	}
	
	public void backToLogin(Stage stage) {
		try {
			Pane root = FXMLLoader.load(getClass().getResource("AuthentificationScreen.fxml"));
			Scene scene = new Scene(root,1200,720);
			scene.getStylesheets().add(getClass().getResource("Application.css").toExternalForm());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
