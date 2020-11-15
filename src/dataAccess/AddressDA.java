package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Address;

public class AddressDA implements IAddressDA {

	private static final String useDBQuery = "USE BookBayDB;";
	
	private static final String addAddressQuery = "INSERT INTO Address(Street, City, State, ZipCode) "
												+ "VALUES(?, ?, ?, ?);";
	
	private static final String getAddressByIDQuery = "SELECT * FROM Address "
												  + "WHERE AddressID = ?;";
	
	private static final String getLastAddressQuery = "SELECT * FROM Address "
													+ "ORDER BY AddressID DESC LIMIT 1;";
	
	private static final String updateAddressQuery = "UPDATE Address "
												   + "SET Street = ?,"
												   + "City = ?,"
												   + "State = ?,"
												   + "ZipCode = ? "
												   + "WHERE AddressID = ?;";
	
	private static String deleteAddressQuery = "DELETE FROM Address WHERE AddressID = ?;";
	
	@Override
	public void createAddress(Address address) throws SQLException {
		String street = address.getStreet();
		String city = address.getCity();
		String state = address.getState();
		int zipCode = address.getZipCode();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement addAddressStmt = connection.prepareStatement(addAddressQuery);
		addAddressStmt.setString(1, street);
		addAddressStmt.setString(2, city);
		addAddressStmt.setString(3, state);
		addAddressStmt.setInt(4, zipCode);
			
		addAddressStmt.executeUpdate();
			
		connection.close();
	}

	@Override
	public Address getAddressByID(int addressID) throws SQLException {
		Address address = null;
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getAddressPstmt = connection.prepareStatement(getAddressByIDQuery);
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
		    
		    connection.close();
		    
		    return address;
		}
		    
		connection.close();
		 
		
		return address;
	}

	@Override
	public Address getLastAddress() throws SQLException {
		Address address = null;
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getLastAddressPstmt = connection.prepareStatement(getLastAddressQuery);
	    
		ResultSet addressRS = getLastAddressPstmt.executeQuery();
		    
		while(addressRS.next()) {
			address = new Address();
			
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
		    
		    connection.close();
		    
		    return address;
		}
		    
		connection.close();
		
		return address;
	}

	@Override
	public void updateAddress(Address address) throws SQLException {
		int addressID = address.getAddressID();
		String street = address.getStreet();
		String city = address.getCity();
		String state = address.getState();
		int zipCode = address.getZipCode();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement updateAddressStmt = connection.prepareStatement(updateAddressQuery);
		updateAddressStmt.setString(1, street);
		updateAddressStmt.setString(2, city);
		updateAddressStmt.setString(3, state);
		updateAddressStmt.setInt(4, zipCode);
		updateAddressStmt.setInt(5, addressID);
		
		updateAddressStmt.executeUpdate();
		
		connection.close();
	}

	@Override
	public void deleteAddress(Address address) throws SQLException {
		int addressID = address.getAddressID();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement deleteAddressStmt = connection.prepareStatement(deleteAddressQuery);
		deleteAddressStmt.setInt(1, addressID);
		
		deleteAddressStmt.executeUpdate();
		
		connection.close();
	}

}
