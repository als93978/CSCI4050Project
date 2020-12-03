package dataAccess;

import java.sql.SQLException;

import models.ShoppingCart;

public interface IShoppingCartDA {
	public void createShoppingCart(ShoppingCart cart) throws SQLException;
	
	public ShoppingCart getShoppingCartByUserID(int userID) throws SQLException;
	
	public void updateShoppingCart(ShoppingCart cart) throws SQLException;
}
