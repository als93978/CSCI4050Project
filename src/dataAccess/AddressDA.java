package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Address;
import models.User;
import models.UserStatus;
import models.UserType;

public class AddressDA {
	
	private static String dbURL = "jdbc:mysql://localhost:3306/BookBayDB?serverTimezone=UTC";
	
	public static void addAddressToDB(Address address) {
		// Get all the values from Address
		String street = address.getStreet();
		String city = address.getCity();
		String state = address.getState();
		int zipCode = address.getZipCode();
		
		String useDBQuery = "USE BookBayDB;";
		
		String addUserQuery = "INSERT INTO Address(Street, City, State, ZipCode) "
							+ "VALUES(?, ?, ?, ?);";
		
		String dbUsername = "root";
		String dbPassword = "ajgopattymn7890";
		
		try {
			Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			
			PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
			useDBStmt.executeQuery();
			
			PreparedStatement addUserStmt = connection.prepareStatement(addUserQuery);
			addUserStmt.setString(1, street);
			addUserStmt.setString(2, city);
			addUserStmt.setString(3, state);
			addUserStmt.setInt(4, zipCode);
			
			addUserStmt.executeUpdate();
			
			connection.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Address getLastAddressFromDB() {
		Address address = new Address();
		
		String useDBQuery = "USE BookBayDB;";
		
		String getLastAddressQuery = "SELECT * FROM Address "
							       + "ORDER BY AddressID DESC LIMIT 1;";
		
		String dbUsername = "root";
		String dbPassword = "ajgopattymn7890";
		
		try {
			Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			
		    PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		    useDBStmt.executeQuery();
		    
		    PreparedStatement getLastAddressPstmt = connection.prepareStatement(getLastAddressQuery);
		    
		    ResultSet lastAddressRS = getLastAddressPstmt.executeQuery();
		    
		    while(lastAddressRS.next()) {
		    	int addressID = lastAddressRS.getInt(1);
		    	String street = lastAddressRS.getString(2);
		    	String city = lastAddressRS.getString(3);
		    	String state = lastAddressRS.getString(4);
		    	int zipCode = lastAddressRS.getInt(5);
		    	
		    	address.setAddressID(addressID);
		    	address.setStreet(street);
		    	address.setCity(city);
		    	address.setState(state);
		    	address.setZipCode(zipCode);
		    }
		    
		    connection.close();
		    
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return address;
	}
}
