package application.database;

import java.sql.Connection;
import java.sql.DriverManager;
 
public class SqlConnection {
	public static Connection Connector() {
		String serverUrl="localhost";
		String dbName="Csharp_Platforme";

		
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			Connection connection = DriverManager.getConnection("jdbc:sqlserver://"+ serverUrl +";databaseName="+ dbName +";integratedSecurity=true;");
			return connection;	
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
		return null;
	}
}
