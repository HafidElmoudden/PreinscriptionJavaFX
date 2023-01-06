module javafxproject {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires java.sql;
	requires com.microsoft.sqlserver.jdbc;
	requires javafx.graphics;
	requires javafx.base;
	
	//exports controllers and opens them
	exports application.controllers;
	opens application.controllers;
	
	exports application.entities;
	opens application.entities;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
}
