package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.PaymentCard;

public class PaymentCardDA {
	
	public static String dbURL = "jdbc:mysql://localhost:3306/BookBayDB?serverTimezone=UTC";
	
	public static void addPaymentCardToDB(PaymentCard paymentCard) {
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
	
}
