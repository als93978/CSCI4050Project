package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.CryptoHelper;
import models.Address;
import models.CardType;
import models.PaymentCard;

public class PaymentCardDA implements IPaymentCardDA {

	private static final String useDBQuery = "USE BookBayDB;";
	
	private static final String addPaymentCardQuery = "INSERT INTO PaymentCard(CardNum, `Type`, ExpDate, UserID) "
			   										+ "VALUES(?, ?, ?, ?)";
	
	private static final String getPaymentCardByUserIDQuery = "SELECT * FROM PaymentCard "
															+ "WHERE UserID = ?";
	
	private static final String getPaymentCardByCardNumQuery = "SELECT * FROM PaymentCard "
															 + "WHERE CardNum = ?";
	
	private static final String getLastPaymentCardQuery = "SELECT * FROM PaymentCard "
														+ "ORDER BY CardNum DESC LIMIT 1;";
	
	private static final String updatePaymentCardQuery = "UPDATE Address "
													   + "SET CardNum = ?,"
													   + "`Type` = ?,"
													   + "ExpDate = ? "
													   + "WHERE UserID = ?;";
	
	private static final String updateCardNumQuery = "UPDATE PaymentCard SET CardNum = ? WHERE UserID = ?;";
	
	private static final String deletePaymentCardQuery = "DELETE FROM PaymentCard WHERE CardNum = ?;";
	
	@Override
	public void createPaymentCard(PaymentCard paymentCard) throws SQLException {
		String cardNum = paymentCard.getCardNum();
		String cardNumEncrypted = CryptoHelper.encryptText(cardNum);
		
		String cardType = paymentCard.getCardType().name();
		String expDate = paymentCard.getExpDate();
		int userID = paymentCard.getUserID();
		
		Connection connection = DataAccessHelper.getInstance().getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement addPaymentCardStmt = connection.prepareStatement(addPaymentCardQuery);
		addPaymentCardStmt.setString(1, cardNumEncrypted);
		addPaymentCardStmt.setString(2, cardType);
		addPaymentCardStmt.setString(3, expDate);
		addPaymentCardStmt.setInt(4, userID);
			
		addPaymentCardStmt.executeUpdate();
			
		connection.close();
	}
	
	@Override
	public PaymentCard getPaymentCardByUserID(int userID) throws SQLException {
		PaymentCard paymentCard = null;
		
		Connection connection = DataAccessHelper.getInstance().getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getPaymentCardByUserIDPstmt = connection.prepareStatement(getPaymentCardByUserIDQuery);
		getPaymentCardByUserIDPstmt.setInt(1, userID);    
		
		ResultSet paymentCardRS = getPaymentCardByUserIDPstmt.executeQuery();
		    
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
		    
		    connection.close();
		    
		    return paymentCard;
		}
		    
		connection.close();
		 
		
		return paymentCard;
	}

	@Override
	public PaymentCard getPaymentCardByCardNum(String cardNum) throws SQLException {
		PaymentCard paymentCard = null;
		
		Connection connection = DataAccessHelper.getInstance().getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getPaymentCardByCardNumPstmt = connection.prepareStatement(getPaymentCardByCardNumQuery);
		getPaymentCardByCardNumPstmt.setString(1, cardNum);    
		
		ResultSet paymentCardRS = getPaymentCardByCardNumPstmt.executeQuery();
		    
		while(paymentCardRS.next()) {
			paymentCard = new PaymentCard();
			
			String dbCardNum = CryptoHelper.decryptText(paymentCardRS.getString(1));
			CardType cardType = CardType.valueOf(paymentCardRS.getString(2));
			String expDate = paymentCardRS.getString(3);
			int dbUserID = paymentCardRS.getInt(4);
			
			paymentCard.setCardNum(dbCardNum);
			paymentCard.setCardType(cardType);
			paymentCard.setExpDate(expDate);
			paymentCard.setUserID(dbUserID);
		    
		    connection.close();
		    
		    return paymentCard;
		}
		    
		connection.close();
		 
		
		return paymentCard;
	}

	@Override
	public PaymentCard getLastPaymentCard() throws SQLException {
		PaymentCard paymentCard = null;
		
		Connection connection = DataAccessHelper.getInstance().getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getLastPaymentCardPstmt = connection.prepareStatement(getLastPaymentCardQuery);  
		
		ResultSet paymentCardRS = getLastPaymentCardPstmt.executeQuery();
		    
		while(paymentCardRS.next()) {
			paymentCard = new PaymentCard();
			
			String dbCardNum = CryptoHelper.decryptText(paymentCardRS.getString(1));
			CardType cardType = CardType.valueOf(paymentCardRS.getString(2));
			String expDate = paymentCardRS.getString(3);
			int dbUserID = paymentCardRS.getInt(4);
			
			paymentCard.setCardNum(dbCardNum);
			paymentCard.setCardType(cardType);
			paymentCard.setExpDate(expDate);
			paymentCard.setUserID(dbUserID);
		    
		    connection.close();
		    
		    return paymentCard;
		}
		    
		connection.close();
		 
		
		return paymentCard;
	}

	@Override
	public void updatePaymentCard(PaymentCard paymentCard) throws SQLException {
		String cardNum = paymentCard.getCardNum();
		String cardType = paymentCard.getCardType().name();
		String expDate = paymentCard.getExpDate();
		int userID = paymentCard.getUserID();
		
		Connection connection = DataAccessHelper.getInstance().getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement updatePaymentCardStmt = connection.prepareStatement(updatePaymentCardQuery);
		updatePaymentCardStmt.setString(1, cardNum);
		updatePaymentCardStmt.setString(2, cardType);
		updatePaymentCardStmt.setString(3, expDate);
		updatePaymentCardStmt.setInt(4, userID);
			
		updatePaymentCardStmt.executeUpdate();
			
		connection.close();
	}
	
	@Override
	public void updateCardNum(PaymentCard paymentCard) throws SQLException {
		String cardNum = paymentCard.getCardNum();
		int userID = paymentCard.getUserID();
		
		Connection connection = DataAccessHelper.getInstance().getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement updateCardNumStmt = connection.prepareStatement(updateCardNumQuery);
		
		String cardNumEncrypted = CryptoHelper.encryptText(cardNum);
		updateCardNumStmt.setString(1, cardNumEncrypted);
		updateCardNumStmt.setInt(2, userID);
		
		updateCardNumStmt.executeUpdate();
			
		connection.close();
	}

	@Override
	public void deletePaymentCard(PaymentCard paymentCard) throws SQLException {
		String cardNum = paymentCard.getCardNum();
		
		Connection connection = DataAccessHelper.getInstance().getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement deletePaymentCardStmt = connection.prepareStatement(deletePaymentCardQuery);
		deletePaymentCardStmt.setString(1, cardNum);
		
		deletePaymentCardStmt.executeUpdate();
		
		connection.close();
	}

}
