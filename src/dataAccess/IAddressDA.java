package dataAccess;

import java.sql.SQLException;

import models.Address;
import models.User;

public interface IAddressDA {
	public void createAddress(Address address) throws SQLException;
	
	public Address getAddressByID(int addressID) throws SQLException;
	
	public Address getLastAddress() throws SQLException;
	
	public void updateAddress(Address address) throws SQLException;
	
	public void deleteAddress(Address address) throws SQLException;
}
