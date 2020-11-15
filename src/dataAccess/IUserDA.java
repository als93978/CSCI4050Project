package dataAccess;

import java.sql.SQLException;

import models.User;

public interface IUserDA {
	public void createUser(User user) throws SQLException;
	
	public User getUserByID(int userID) throws SQLException;
	public User getUserByEmail(String email) throws SQLException;
	
	public User getLastUser() throws SQLException;
	
	public void updateUser(User user) throws SQLException;
	
	public void updatePassword(User user) throws SQLException;
	
	public void deleteUser(User user) throws SQLException;
}
