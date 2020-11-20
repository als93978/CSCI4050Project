package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controllers.CryptoHelper;
import models.User;
import models.UserStatus;
import models.UserType;
import models.Promotion;

public interface IPromotionDA {

	public void createPromotion(Promotion promotion) throws SQLException;
	
	public List<Promotion> getAllPromotions() throws SQLException;

	public Promotion getPromotionByCode(int promotionCode) throws SQLException;
	
	public Promotion getLastPromotion() throws SQLException;
}
