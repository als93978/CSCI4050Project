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
	
	public static void addAddressToDB(Address address) throws SQLException {
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
	}
	
	public static Address getLastAddressFromDB() throws SQLException {
		Address address = new Address();
		
		String useDBQuery = "USE BookBayDB;";
		
		String getLastAddressQuery = "SELECT * FROM Address "
							       + "ORDER BY AddressID DESC LIMIT 1;";
		
		String dbUsername = "root";
		String dbPassword = "ajgopattymn7890";
		
		Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		    
		PreparedStatement getLastAddressPstmt = connection.prepareStatement(getLastAddressQuery);
		    
		ResultSet addressRS = getLastAddressPstmt.executeQuery();
		    
		while(addressRS.next()) {
			int addressID = addressRS.getInt(1);
		    String street = addressRS.getString(2);
		    String city = addressRS.getString(3);
		    String state = addressRS.getString(4);
		    int zipCode = addressRS.getInt(5);
		    	
		    address.setAddressID(addressID);
		    address.setStreet(street);
		    address.setCity(city);
		    address.setState(state);
		    address.setZipCode(zipCode);
		}
		    
		connection.close();
		 
		
		return address;
	}
	
	public static Address getAddress(int addressID) throws SQLException {
		Address address = null;
		
		String useDBQuery = "USE BookBayDB;";
		
		String getAddressQuery = "SELECT * FROM Address "
							       + "WHERE AddressID = ?;";
		
		String dbUsername = "root";
		String dbPassword = "ajgopattymn7890";
		
		Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		    
		PreparedStatement getAddressPstmt = connection.prepareStatement(getAddressQuery);
		getAddressPstmt.setInt(1, addressID);    
		
		ResultSet addressRS = getAddressPstmt.executeQuery();
		    
		while(addressRS.next()) {
			address = new Address();
			
			int dbAddressID = addressRS.getInt(1);
		    String street = addressRS.getString(2);
		    String city = addressRS.getString(3);
		    String state = addressRS.getString(4);
		    int zipCode = addressRS.getInt(5);
		    	
		    address.setAddressID(dbAddressID);
		    address.setStreet(street);
		    address.setCity(city);
		    address.setState(state);
		    address.setZipCode(zipCode);
		}
		    
		connection.close();
		 
		
		return address;
	}

	public static<T> void editAddressValue(int addressID, String colName, T newValue) throws SQLException {
		String useDBQuery = "USE BookBayDB;";
		
		String addAddressQuery = "UPDATE `Address` SET " + colName + " = ? WHERE AddressID = ?;";
		
		String dbUsername = "root";
		String dbPassword = "ajgopattymn7890";
		
		Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
			
		PreparedStatement addAddressStmt = connection.prepareStatement(addAddressQuery);
		addAddressStmt.setObject(1, newValue);
		addAddressStmt.setInt(2, addressID);
			
		addAddressStmt.executeUpdate();
			
		connection.close();
	}
	
	public static<T> T getAddressValue(String colName, String identifier, String identifierValue) throws SQLException {
		String useDBQuery = "USE BookBayDB;";
		
		String getUserValueQuery = "SELECT " + colName + " FROM Address "
								 + "WHERE " + identifier + " = ?;";
		
		String dbUsername = "root";
		String dbPassword = "ajgopattymn7890";
		
		Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getAddressValueStmt = connection.prepareStatement(getUserValueQuery);
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
