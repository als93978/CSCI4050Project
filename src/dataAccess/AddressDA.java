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
    
		String country = address.getCountry();
		
		String useDBQuery = "USE BookBayDB;";
		
		String addUserQuery = "INSERT INTO Address(Street, City, State, ZipCode, Country) "
							+ "VALUES(?, ?, ?, ?, ?);";
		
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
			addUserStmt.setString(5, country);
			
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
			    String country = lastAddressRS.getString(6);
		    	
		    	address.setAddressID(addressID);
		    	address.setStreet(street);
		    	address.setCity(city);
		    	address.setState(state);
		    	address.setZipCode(zipCode);
			    address.setCountry(country);
		    }
		    
		    connection.close();
		    
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return address;
	}

	public static<T> void editAddressValue(int addressID, String colName, T newValue) {
		String useDBQuery = "USE BookBayDB;";
		
		String addAddressQuery = "UPDATE `Address` SET " + colName + " = ? WHERE AddressID = ?;";
		
		String dbUsername = "root";
		String dbPassword = "ajgopattymn7890";
		
		try {
			Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			
			PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
			useDBStmt.executeQuery();
			
			PreparedStatement addAddressStmt = connection.prepareStatement(addAddressQuery);
			addAddressStmt.setObject(1, newValue);
			addAddressStmt.setInt(2, addressID);
			
			addAddressStmt.executeUpdate();
			
			connection.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static<T> T getAddressValue(String colName, String identifier, String identifierValue) throws SQLException {
		String useDBQuery = "USE BookBayDB;";
		
		String getAddressValueQuery = "SELECT " + colName + " FROM Address "
								 + "WHERE " + identifier + " = ?;";
		
		String dbUsername = "root";
		String dbPassword = "ajgopattymn7890";
		
		Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getAddressValueStmt = connection.prepareStatement(getAddressValueQuery);
		getAddressValueStmt.setString(1, identifierValue);
		
		ResultSet addressValueRS = getAddressValueStmt.executeQuery();
		
		T value = null;
		while(addressValueRS.next()) {
			value = (T) addressValueRS.getObject(1);
		}
		    
		connection.close();
		
		return value;
	}
}
