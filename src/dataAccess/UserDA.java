package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.CryptoHelper;
import models.User;
import models.UserStatus;
import models.UserType;

public class UserDA {
	
	private static String dbURL = "jdbc:mysql://localhost:3306/BookBayDB?serverTimezone=UTC";
	
	public static void addUserToDB(User user) throws Exception {
		// Get all the values from User
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		
		String password = user.getPassword();
		String passwordEncrypted = CryptoHelper.encrypt(password);
		
		// Using strings seems to be easiest way to insert enum to DB
		String status = user.getStatus().name();
		boolean enrollmentForPromotions = user.getEnrollmentForPromotions();
		int numOfCards = user.getNumOfCards();
		String type = user.getType().name();
		
		String useDBQuery = "USE BookBayDB;";
		
		String addUserQuery = "INSERT INTO `User`(FirstName, LastName, Email, `Password`,"
							+ "`Status`, EnrollmentForPromotions, NumOfCards, Type) "
							+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		
		String dbUsername = "root";
		String dbPassword = "runningislife15";
	
		Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
			
		PreparedStatement addUserStmt = connection.prepareStatement(addUserQuery);
		addUserStmt.setString(1, firstName);
		addUserStmt.setString(2, lastName);
		addUserStmt.setString(3, email);
		addUserStmt.setString(4, passwordEncrypted);
		addUserStmt.setString(5, status);
		addUserStmt.setBoolean(6, enrollmentForPromotions);
		addUserStmt.setInt(7, numOfCards);
		addUserStmt.setString(8, type);
		
		addUserStmt.executeUpdate();
		
		connection.close();
	}
	
	public static User getLastUserFromDB() throws Exception {
		User user = new User();
		
		String useDBQuery = "USE BookBayDB;";
		
		String getLastUserQuery = "SELECT * FROM User "
							    + "ORDER BY UserID DESC LIMIT 1;";
		
		String dbUsername = "root";
		String dbPassword = "runningislife15";
		
		Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		    
		PreparedStatement getLastUserPstmt = connection.prepareStatement(getLastUserQuery);
		    
		ResultSet userRS = getLastUserPstmt.executeQuery();
		    
		while(userRS.next()) {
			int userID = userRS.getInt(1);
		    String firstName = userRS.getString(2);
		    String lastName = userRS.getString(3);
		    String email = userRS.getString(4);
		    
		    String passwordEncrypted = userRS.getString(5);
		    String password = CryptoHelper.decrypt(passwordEncrypted);
		    
		    UserStatus status = UserStatus.valueOf(userRS.getString(6));
		    boolean enrollmentForPromotions = userRS.getBoolean(7);
		    int numOfCards = userRS.getInt(8);
		    String confirmationCode = userRS.getString(9);
		    UserType type = UserType.valueOf(userRS.getString(10));
		    int addressID = userRS.getInt(11);
		    	
		    user.setUserID(userID);
		    user.setFirstName(firstName);
		    user.setLastName(lastName);
		    user.setEmail(email);
		    user.setPassword(password);
		    user.setStatusID(status);
		    user.setEnrollmentForPromotions(enrollmentForPromotions);
		    user.setNumOfCards(numOfCards);
		    user.setConfirmationCode(confirmationCode);
		    user.setType(type);
		    user.setAddressID(addressID);
		}
		    
		connection.close();
		
		return user;
	}
	
	public static User getUser(int userID) throws Exception {
		User user = new User();
		
		String useDBQuery = "USE BookBayDB;";
		
		String getUserQuery = "SELECT * FROM User "
							    + "WHERE UserID = ?;";
		
		String dbUsername = "root";
		String dbPassword = "runningislife15";
		
		Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		    
		PreparedStatement getUserPstmt = connection.prepareStatement(getUserQuery);
		getUserPstmt.setInt(1, userID);    
		
		ResultSet userRS = getUserPstmt.executeQuery();
		    
		while(userRS.next()) {
			int dbUserID = userRS.getInt(1);
		    String firstName = userRS.getString(2);
		    String lastName = userRS.getString(3);
		    String email = userRS.getString(4);
		    
		    String passwordEncrypted = userRS.getString(5);
		    String password = CryptoHelper.decrypt(passwordEncrypted);
		    
		    UserStatus status = UserStatus.valueOf(userRS.getString(6));
		    boolean enrollmentForPromotions = userRS.getBoolean(7);
		    int numOfCards = userRS.getInt(8);
		    String confirmationCode = userRS.getString(9);
		    UserType type = UserType.valueOf(userRS.getString(10));
		    int addressID = userRS.getInt(11);
		    
		    user.setUserID(dbUserID);
		    user.setFirstName(firstName);
		    user.setLastName(lastName);
		    user.setEmail(email);
		    user.setPassword(password);
		    user.setStatusID(status);
		    user.setEnrollmentForPromotions(enrollmentForPromotions);
		    user.setNumOfCards(numOfCards);
		    user.setConfirmationCode(confirmationCode);
		    user.setType(type);
		    user.setAddressID(addressID);
		}
		    
		connection.close();
		
		return user;
	}
	
	public static<T> void editUserValue(int userID, String colName, T newValue) throws SQLException {
		String useDBQuery = "USE BookBayDB;";
		
		String addUserQuery = "UPDATE `User` SET " + colName + " = ? WHERE UserID = ?;";
		
		String dbUsername = "root";
		String dbPassword = "runningislife15";
		
		Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
			
		PreparedStatement addUserStmt = connection.prepareStatement(addUserQuery);
		
		addUserStmt.setObject(1, newValue);
		addUserStmt.setInt(2, userID);
			
		addUserStmt.executeUpdate();
			
		connection.close();
	}
	
	public static void editUserValueEncrypt(int userID, String colName, String newValue) throws Exception {
		String useDBQuery = "USE BookBayDB;";
		
		String addUserQuery = "UPDATE `User` SET " + colName + " = ? WHERE UserID = ?;";
		
		String dbUsername = "root";
		String dbPassword = "runningislife15";
		
		Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
			
		PreparedStatement addUserStmt = connection.prepareStatement(addUserQuery);
		
		String newValueEncrypted = CryptoHelper.encrypt(newValue);
		
		addUserStmt.setString(1, newValueEncrypted);
		addUserStmt.setInt(2, userID);
			
		addUserStmt.executeUpdate();
			
		connection.close();
	}
	
	public static<T> T getUserValue(String colName, String identifier, String identifierValue) throws SQLException {
		String useDBQuery = "USE BookBayDB;";
		
		String getUserValueQuery = "SELECT " + colName + " FROM User "
								 + "WHERE " + identifier + " = ?;";
		
		String dbUsername = "root";
		String dbPassword = "runningislife15";
		
		Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getUserValueStmt = connection.prepareStatement(getUserValueQuery);
		getUserValueStmt.setString(1, identifierValue);
		
		ResultSet userValueRS = getUserValueStmt.executeQuery();
		
		T value = null;
		while(userValueRS.next()) {
			value = (T) userValueRS.getObject(1);
		}
		    
		connection.close();
		
		return value;
	}
	
	public static String getUserValueEncrypted(String colName, String identifier, String identifierValue) throws Exception {
		String useDBQuery = "USE BookBayDB;";
		
		String getUserValueQuery = "SELECT " + colName + " FROM User "
								 + "WHERE " + identifier + " = ?;";
		
		String dbUsername = "root";
		String dbPassword = "runningislife15";
		
		Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getUserValueStmt = connection.prepareStatement(getUserValueQuery);
		getUserValueStmt.setString(1, identifierValue);
		
		ResultSet userValueRS = getUserValueStmt.executeQuery();
		
		String value = "";
		while(userValueRS.next()) {
			String valueEncrypted = userValueRS.getString(1);
			value = CryptoHelper.decrypt(valueEncrypted);
		}
		    
		connection.close();
		
		return value;
	}
	
}
