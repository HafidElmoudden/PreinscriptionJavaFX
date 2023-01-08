package application.database;

import java.sql.Connection;
import java.sql.DriverManager;
 
public class SqlConnection {
	public static Connection Connector() {
		String instanceName;
		
		instanceName="ABDULHAFID\\SQLEXPRESS";
		//instanceName="DESKTOP-3HP6PGB\\SQLEXPRESS";
		String dbName="Csharp_Platforme";
		
		String db_url = "jdbc:sqlserver://"+instanceName+";databaseName="+dbName+";integratedSecurity=true;trustServerCertificate=true";
		Connection connection = null;
		
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
				connection = DriverManager.getConnection(db_url);
				
			return connection;	
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
		return connection;
	}
}
