package prj_bd;
import java.sql.*;

public class ConnectionManager {
		
	private static Connection conn;
	
	private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	private static String DB_URL = "jdbc:mysql://localhost:3306/prj_bd?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false";
	private static String USER = "Lynk";
	private static String PASS = "cripadriKa10^^"; 
		
public static Connection getConnection(){
					
	try {
		
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		
	}catch(SQLException e) {
		
		e.printStackTrace();
		System.out.println("SQL Exception");
		
	}catch(ClassNotFoundException e) {
		
		e.printStackTrace();
		System.out.println("Driver Not Found");
	}
	
			return conn;
	}

}

