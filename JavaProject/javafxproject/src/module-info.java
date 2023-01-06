module javafxproject {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires java.sql;
	requires com.microsoft.sqlserver.jdbc;
	
	//exports controllers and opens them
	exports application.controllers;
	opens application.controllers;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
}
