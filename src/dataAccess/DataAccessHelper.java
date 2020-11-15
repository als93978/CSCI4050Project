package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataAccessHelper {
	private static final String dbURL = "jdbc:mysql://localhost:3306/BookBayDB?serverTimezone=UTC";
	private static final String dbUsername = "root";
	private static final String dbPassword = "*LBBVh610gX98SEYOEtEMYRo5";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbURL, dbUsername, dbPassword);
	}
	
}
