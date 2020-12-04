package dataAccess;

import java.sql.SQLException;

import models.Order;

public interface IOrderDA {
	public void createOrder(Order order) throws SQLException;
	
	public Order getNonSubmittedOrderByUserID(int userID) throws SQLException;
	
	public void updateOrder(Order order) throws SQLException;
}
