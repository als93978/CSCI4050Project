package dataAccess;

import java.sql.SQLException;
import java.util.List;

import models.Promotion;

public class PromotionDA implements IPromotionDA {
	
	private static final String useDBQuery = "USE BookBayDB";
	
	private static final String addPromotionQuery = "INSERT INTO `Promotion`(PromotionCode, Percentage, ExpDate, StartDate) VALUES(?,?,?,?);";
	
	private static final String getAllPromotionsQuery = "SELECT * FROM Promotion";
	
	private static final String getPromotionByCode = "SELECT * FROM Promotion WHERE PromotionCode = ?;";

	@Override
	public void createPromotion(Promotion promotion) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Promotion> getAllPromotions() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Promotion getPromotionByCode(int promotionCode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
