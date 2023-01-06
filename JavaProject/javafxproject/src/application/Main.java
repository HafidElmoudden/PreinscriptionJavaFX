package application;
	
import java.sql.Connection;
import java.sql.DatabaseMetaData;

import application.database.SqlConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root=FXMLLoader.load(getClass().getResource("AdminScreen.fxml"));
			Scene scene = new Scene(root,1200,720);
			scene.getStylesheets().add(getClass().getResource("Application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
