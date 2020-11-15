package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.CryptoHelper;
import models.User;
import models.UserStatus;
import models.UserType;

public class UserDA implements IUserDA {

	private static final String useDBQuery = "USE BookBayDB";
	
	private static final String addUserQuery = "INSERT INTO `User`(FirstName, LastName, Email, `Password`,"
			+ "`Status`, EnrollmentForPromotions, NumOfCards, Type) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
	
	private static final String getUserByIDQuery = "SELECT * FROM User "
												 + "WHERE UserID = ?;";
	
	private static final String getUserByEmailQuery = "SELECT * FROM User "
			 								        + "WHERE Email = ?;";
	
	private static final String getLastUserQuery = "SELECT * FROM User "
		    									 + "ORDER BY UserID DESC LIMIT 1;";
	
	private static final String updateUserQuery = "UPDATE `User` "
			 									+ "SET FirstName = ?,"
			 									+ "LastName = ?,"
			 									+ "Email = ?,"
			 									+ "`Password` = ?,"
			 									+ "`Status` = ?,"
			 									+ "EnrollmentForPromotions = ?,"
			 									+ "NumOfCards = ?,"
			 									+ "ConfirmationCode = ?,"
			 									+ "`Type` = ?,"
			 									+ "AddressID = ? "
			 									+ "WHERE UserID = ?;";
	
	private static String updatePasswordQuery = "UPDATE `User` SET `Password` = ? WHERE UserID = ?;";
	
	private static String deleteUserQuery = "DELETE FROM `User` WHERE UserID = ?;";
	
	@Override
	public void createUser(User user) throws SQLException {
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		
		String password = user.getPassword();
		String passwordEncrypted = CryptoHelper.encryptPassword(password);

		String status = user.getStatus().name();
		boolean enrollmentForPromotions = user.getEnrollmentForPromotions();
		int numOfCards = user.getNumOfCards();
		String type = user.getType().name();
		
		Connection connection = DataAccessHelper.getConnection();
		
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

	@Override
	public User getUserByID(int userID) throws SQLException {
		User user = null;
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		    
		PreparedStatement getUserByIDPstmt = connection.prepareStatement(getUserByIDQuery);
		getUserByIDPstmt.setInt(1, userID);    
		
		ResultSet userRS = getUserByIDPstmt.executeQuery();
		    
		while(userRS.next()) {
			user = new User();
			
			int dbUserID = userRS.getInt(1);
		    String firstName = userRS.getString(2);
		    String lastName = userRS.getString(3);
		    String email = userRS.getString(4);
		    String password = userRS.getString(5);
		    
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
		    
		    connection.close();
		    
		    return user;
		}
		    
		connection.close();
		
		return user;
	}
	
	@Override
	public User getUserByEmail(String email) throws SQLException {
		User user = null;
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		    
		PreparedStatement getUserByEmailPstmt = connection.prepareStatement(getUserByEmailQuery);
		getUserByEmailPstmt.setString(1, email);    
		
		ResultSet userRS = getUserByEmailPstmt.executeQuery();
		    
		while(userRS.next()) {
			user = new User();
			
			int dbUserID = userRS.getInt(1);
		    String firstName = userRS.getString(2);
		    String lastName = userRS.getString(3);
		    String dbEmail = userRS.getString(4);
		    String password = userRS.getString(5);
		    
		    UserStatus status = UserStatus.valueOf(userRS.getString(6));
		    boolean enrollmentForPromotions = userRS.getBoolean(7);
		    int numOfCards = userRS.getInt(8);
		    String confirmationCode = userRS.getString(9);
		    UserType type = UserType.valueOf(userRS.getString(10));
		    int addressID = userRS.getInt(11);
		    
		    user.setUserID(dbUserID);
		    user.setFirstName(firstName);
		    user.setLastName(lastName);
		    user.setEmail(dbEmail);
		    user.setPassword(password);
		    user.setStatusID(status);
		    user.setEnrollmentForPromotions(enrollmentForPromotions);
		    user.setNumOfCards(numOfCards);
		    user.setConfirmationCode(confirmationCode);
		    user.setType(type);
		    user.setAddressID(addressID);
		    
		    connection.close();
		    
		    return user;
		}
		    
		connection.close();
		
		return user;
	}
	
	@Override
	public User getLastUser() throws SQLException {
		User user = null;
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		    
		PreparedStatement getLastUserPstmt = connection.prepareStatement(getLastUserQuery); 
		
		ResultSet userRS = getLastUserPstmt.executeQuery();
		    
		while(userRS.next()) {
			user = new User();
			
			int dbUserID = userRS.getInt(1);
		    String firstName = userRS.getString(2);
		    String lastName = userRS.getString(3);
		    String email = userRS.getString(4);
		    String password = userRS.getString(5);
		    
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
		    
		    connection.close();
		    
		    return user;
		}
		    
		connection.close();
		
		return user;
	}

	@Override
	public void updateUser(User user) throws SQLException {
		int userID = user.getUserID();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		String password = user.getPassword();
		String status = user.getStatus().name();
		boolean enrollmentForPromotions = user.getEnrollmentForPromotions();
		int numOfCards = user.getNumOfCards();
		String confirmationCode = user.getConfirmationCode();
		String type = user.getType().name();
		int addressID = user.getAddressID();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement updateUserStmt = connection.prepareStatement(updateUserQuery);
		
		updateUserStmt.setString(1, firstName);
		updateUserStmt.setString(2, lastName);
		updateUserStmt.setString(3, email);
		updateUserStmt.setString(4, password);
		updateUserStmt.setString(5, status);
		updateUserStmt.setBoolean(6, enrollmentForPromotions);
		updateUserStmt.setInt(7, numOfCards);
		updateUserStmt.setString(8, confirmationCode);
		updateUserStmt.setString(9, type);
		
		if(addressID != 0) {
			updateUserStmt.setInt(10, addressID);
			updateUserStmt.setInt(11, userID);
		}
		
		else {
			updateUserStmt.setObject(10, null);
			updateUserStmt.setInt(11, userID);
		}
		
		updateUserStmt.executeUpdate();
		
		connection.close();
	}

	@Override
	public void updatePassword(User user) throws SQLException {
		String password = user.getPassword();
		int userID = user.getUserID();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement updatePasswordStmt = connection.prepareStatement(updatePasswordQuery);
		
		String passwordEncrypted = CryptoHelper.encryptPassword(password);
		updatePasswordStmt.setString(1, passwordEncrypted);
		updatePasswordStmt.setInt(2, userID);
		
		updatePasswordStmt.executeUpdate();
		
		connection.close();
	}
	
	@Override
	public void deleteUser(User user) throws SQLException {
		int userID = user.getUserID();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement deleteUserStmt = connection.prepareStatement(deleteUserQuery);
		deleteUserStmt.setInt(1, userID);
		
		deleteUserStmt.executeUpdate();
		
		connection.close();
	}

}
