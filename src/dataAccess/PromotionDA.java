package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Promotion;

public class PromotionDA implements IPromotionDA {
	
	private static final String useDBQuery = "USE BookBayDB";
	
	private static final String addPromotionQuery = "INSERT INTO `Promotion`(PromotionCode, Percentage, ExpDate, StartDate) VALUES(?,?,?,?);";
	
	private static final String getAllPromotionsQuery = "SELECT * FROM Promotion";
	
	private static final String getPromotionByCodeQuery = "SELECT * FROM Promotion WHERE PromotionCode = ?;";
	
	private static final String getLastPromotionQuery = "SELECT * FROM Promotion ORDER BY PromotionCode DESC LIMIT 1;";

	@Override
	public void createPromotion(Promotion promotion) throws SQLException {
		int promotionCode = promotion.getPromotionCode();
		int percentage = promotion.getPercentage();
		String expDate = promotion.getExpDate();
		String startDate = promotion.getStartDate();
		
		Connection connection = DataAccessHelper.getConnection();
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		PreparedStatement addPromotionStmt = connection.prepareStatement(addPromotionQuery);
		addPromotionStmt.setInt(1, promotionCode);
		addPromotionStmt.setInt(2, percentage);
		addPromotionStmt.setString(3, expDate);
		addPromotionStmt.setString(4, startDate);
		addPromotionStmt.executeUpdate();
		
		connection.close();

	}

	@Override
	public List<Promotion> getAllPromotions() throws SQLException {
		List<Promotion> promotions = new ArrayList<Promotion>();
		Connection connection = DataAccessHelper.getConnection();
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		PreparedStatement getAllPromotionsStmt = connection.prepareStatement(getAllPromotionsQuery);
		ResultSet allPromotionsRS = getAllPromotionsStmt.executeQuery();
		
		while(allPromotionsRS.next()) {
			Promotion promotion = new Promotion();
			
			int promotionCode = allPromotionsRS.getInt(1);
		    int percentage = allPromotionsRS.getInt(2);
		    String expDate = allPromotionsRS.getString(3);
		    String startDate = allPromotionsRS.getString(4);
		    
		    promotion.setPromotionCode(promotionCode);
		    promotion.setPercentage(percentage);
		    promotion.setExpDate(expDate);
		    promotion.setStartDate(startDate);
		    
		    promotions.add(promotion);
		}
		
		connection.close();
		
		return promotions;
	}

	@Override
	public Promotion getPromotionByCode(int promotionCode) throws SQLException {
		Promotion promotion = null;
		Connection connection = DataAccessHelper.getConnection();
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		PreparedStatement getPromotionByCodePstmt = connection.prepareStatement(getPromotionByCodeQuery);
		getPromotionByCodePstmt.setInt(1, promotionCode);    
		
		ResultSet promotionRS = getPromotionByCodePstmt.executeQuery();
		    
		while(promotionRS.next()) {
			promotion = new Promotion();
			
			int DBpromotionCode = promotionRS.getInt(1);
		    int percentage = promotionRS.getInt(2);
		    String expDate = promotionRS.getString(3);
		    String startDate = promotionRS.getString(4);
		    
		    promotion.setPromotionCode(DBpromotionCode);
		    promotion.setPercentage(percentage);
		    promotion.setExpDate(expDate);
		    promotion.setStartDate(startDate);
		    
		    connection.close();
		    
		    return promotion;
		}
		    
		connection.close();
		
		return promotion;
	}
	
	@Override
	public Promotion getLastPromotion() throws SQLException {
		Promotion promotion = null;
		Connection connection = DataAccessHelper.getConnection();
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		PreparedStatement getLastPromotionPstmt = connection.prepareStatement(getLastPromotionQuery);
		ResultSet promotionRS = getLastPromotionPstmt.executeQuery();
		
		while(promotionRS.next()) {
			promotion = new Promotion();
			
			int promotionCode = promotionRS.getInt(1);
		    int percentage = promotionRS.getInt(2);
		    String expDate = promotionRS.getString(3);
		    String startDate = promotionRS.getString(4);

		    promotion.setPromotionCode(promotionCode);
		    promotion.setPercentage(percentage);
		    promotion.setExpDate(expDate);
		    promotion.setStartDate(startDate);
		    
		    connection.close();
		    
		    return promotion;
		}
		    
		connection.close();
		
		return promotion;
	}

}
