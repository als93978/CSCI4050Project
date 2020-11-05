package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.CryptoHelper;
import models.PaymentCard;

public class PaymentCardDA {
	
	public static String dbURL = "jdbc:mysql://localhost:3306/BookBayDB?serverTimezone=UTC";
	
	public static void addPaymentCardToDB(PaymentCard paymentCard) throws Exception {
		// Get all the values from PaymentCard
		String cardNum = paymentCard.getCardNum();
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
			addPaymentCardStmt.setString(1, cardNum);
			addPaymentCardStmt.setString(2, cardType);
			addPaymentCardStmt.setString(3, expDate);
			addPaymentCardStmt.setInt(4, userID);
			
			addPaymentCardStmt.executeUpdate();
			
			connection.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
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
}
