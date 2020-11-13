package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.CryptoHelper;
import models.Address;
import models.CardType;
import models.PaymentCard;

public class PaymentCardDA {
	
	public static String dbURL = "jdbc:mysql://localhost:3306/BookBayDB?serverTimezone=UTC";
	
	public static void addPaymentCardToDB(PaymentCard paymentCard) throws Exception {
		// Get all the values from PaymentCard
		String cardNum = paymentCard.getCardNum();
		String cardNumEncrypted = CryptoHelper.encryptText(cardNum);
		
		String cardType = paymentCard.getCardType().name();
		String expDate = paymentCard.getExpDate();
		int userID = paymentCard.getUserID();
		
		
		String useDBQuery = "USE BookBayDB";
		
		String addPaymentCardQuery = "INSERT INTO PaymentCard(CardNum, `Type`, ExpDate, UserID) "
								   + "VALUES(?, ?, ?, ?)";
		
		String dbUsername = "root";
		String dbPassword = "ajgopattymn7890";
		
		try {
			Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			
			PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
			useDBStmt.executeQuery();
			
			PreparedStatement addPaymentCardStmt = connection.prepareStatement(addPaymentCardQuery);
			addPaymentCardStmt.setString(1, cardNumEncrypted);
			addPaymentCardStmt.setString(2, cardType);
			addPaymentCardStmt.setString(3, expDate);
			addPaymentCardStmt.setInt(4, userID);
			
			addPaymentCardStmt.executeUpdate();
			
			connection.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static PaymentCard getPaymentCard(int userID) throws Exception {
		PaymentCard paymentCard = null;
		
		String useDBQuery = "USE BookBayDB;";
		
		String getPaymentCardQuery = "SELECT * FROM PaymentCard "
							       + "WHERE UserID = ?";
		
		String dbUsername = "root";
		String dbPassword = "ajgopattymn7890";
		
		Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		    
		PreparedStatement getPaymentCardPstmt = connection.prepareStatement(getPaymentCardQuery);
		getPaymentCardPstmt.setInt(1, userID);    
		
		ResultSet paymentCardRS = getPaymentCardPstmt.executeQuery();
		    
		while(paymentCardRS.next()) {
			paymentCard = new PaymentCard();
	
			String cardNum = CryptoHelper.decryptText(paymentCardRS.getString(1));
			CardType cardType = CardType.valueOf(paymentCardRS.getString(2));
			String expDate = paymentCardRS.getString(3);
			int dbUserID = paymentCardRS.getInt(4);
			
			paymentCard.setCardNum(cardNum);
			paymentCard.setCardType(cardType);
			paymentCard.setExpDate(expDate);
			paymentCard.setUserID(dbUserID);
		}
		    
		connection.close();
		 
		
		return paymentCard;
	}

	public static<T> void editCardValue(int userID, String colName, T newValue) {
		String useDBQuery = "USE BookBayDB;";
		
		String addCardQuery = "UPDATE `PaymentCard` SET " + colName + " = ? WHERE UserID = ?;";
		
		String dbUsername = "root";
		String dbPassword = "ajgopattymn7890";
		
		try {
			Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			
			PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
			useDBStmt.executeQuery();
			
			PreparedStatement addCardStmt = connection.prepareStatement(addCardQuery);
			addCardStmt.setObject(1, newValue);
			addCardStmt.setInt(2, userID);
			
			addCardStmt.executeUpdate();
			
			connection.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static<T> void editPaymentCardNum(int userID, String newValue) throws Exception {
		String useDBQuery = "USE BookBayDB;";
		
		String addCardQuery = "UPDATE `PaymentCard` SET CardNum = ? WHERE UserID = ?;";
		
		String dbUsername = "root";
		String dbPassword = "ajgopattymn7890";
		
		try {
			Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			
			PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
			useDBStmt.executeQuery();
			
			PreparedStatement addCardStmt = connection.prepareStatement(addCardQuery);
			
			String newValueEncrypted = CryptoHelper.encryptText(newValue);
			
			addCardStmt.setString(1, newValueEncrypted);
			addCardStmt.setInt(2, userID);
			
			addCardStmt.executeUpdate();
			
			connection.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static<T> T getPaymentCardValue(String colName, String identifier, String identifierValue) throws SQLException {
		String useDBQuery = "USE BookBayDB;";
		
		String getPaymentCardValueQuery = "SELECT " + colName + " FROM PaymentCard "
								        + "WHERE " + identifier + " = ?;";
		
		String dbUsername = "root";
		String dbPassword = "ajgopattymn7890";
		
		Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getPaymentCardValueStmt = connection.prepareStatement(getPaymentCardValueQuery);
		getPaymentCardValueStmt.setString(1, identifierValue);
		
		ResultSet paymentCardValueRS = getPaymentCardValueStmt.executeQuery();
		
		T value = null;
		while(paymentCardValueRS.next()) {
			value = (T) paymentCardValueRS.getObject(1);
		}
		    
		connection.close();
		
		return value;
	}
	
	public static String getPaymentCardNum(int userID) throws Exception {
		String useDBQuery = "USE BookBayDB;";
		
		String getPaymentCardValueQuery = "SELECT CardNum FROM PaymentCard "
								        + "WHERE UserID = ?;";
		
		String dbUsername = "root";
		String dbPassword = "ajgopattymn7890";
		
		Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getPaymentCardValueStmt = connection.prepareStatement(getPaymentCardValueQuery);
		getPaymentCardValueStmt.setInt(1, userID);
		
		ResultSet paymentCardValueRS = getPaymentCardValueStmt.executeQuery();
		
		String value = "";
		while(paymentCardValueRS.next()) {
			value = CryptoHelper.decryptText(paymentCardValueRS.getString(1));
		}
		
		connection.close();
		
		return value;
	}
}
