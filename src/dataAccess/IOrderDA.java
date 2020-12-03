package dataAccess;

import java.sql.SQLException;

import models.Order;

public interface IOrderDA {
	public void createOrder(Order order) throws SQLException;
	
	public Order getOrderByUserID(int userID) throws SQLException;
}
