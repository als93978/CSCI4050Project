package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.ShoppingCart;

public class ShoppingCartDA implements IShoppingCartDA {

	private static final String useDBQuery = "USE BookBayDB";
	
	private static final String createCartQuery = "INSERT INTO ShoppingCart(UserID) "
			 									+ "VALUES(?);";
	
	private static final String getShoppingCartByUserIDQuery = "SELECT * FROM ShoppingCart "
															 + "WHERE UserID = ?;";
	
	private static final String updateShoppingCartQuery = "UPDATE ShoppingCart "
														+ "SET OrderID = ? "
														+ "WHERE CartID = ?;";
	
	@Override
	public void createShoppingCart(ShoppingCart cart) throws SQLException {
		int userID = cart.getUserID();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement createCartStmt = connection.prepareStatement(createCartQuery);
		createCartStmt.setInt(1, userID);
		
		createCartStmt.executeUpdate();
		
		connection.close();
	}

	@Override
	public ShoppingCart getShoppingCartByUserID(int userID) throws SQLException {
		ShoppingCart cart = null;
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getShoppingCartByUserIDStmt = connection.prepareStatement(getShoppingCartByUserIDQuery);
		getShoppingCartByUserIDStmt.setInt(1, userID);
		
		ResultSet cartRS = getShoppingCartByUserIDStmt.executeQuery();
		
		while(cartRS.next()) {
			cart = new ShoppingCart();
			
			int cartID = cartRS.getInt(1);
			int dbUserID = cartRS.getInt(2);
			int orderID = cartRS.getInt(3);
			
			cart.setCartID(cartID);
			cart.setUserID(dbUserID);
			cart.setOrderID(orderID);
			
			connection.close();
			
			return cart;
		}
		
		connection.close();
		
		return cart;
 	}

	@Override
	public void updateShoppingCart(ShoppingCart cart) throws SQLException {
		int cartID = cart.getCartID();
		int orderID = cart.getOrderID();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement updateShoppingCartStmt = connection.prepareStatement(updateShoppingCartQuery);
		updateShoppingCartStmt.setInt(1, orderID);
		updateShoppingCartStmt.setInt(2, cartID);
	}
}
