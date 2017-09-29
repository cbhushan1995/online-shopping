package net.kzn.shoppingbackend.dao;

import java.util.List;

import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.User;

public interface UserDAO {
	
	//ADD AN USER
	boolean addUser(User user);
	
	User getByEmail(String email);
	
	//add an address
	boolean addAddress(Address address);
	Address getBillingAddress(User user);
	List<Address> listShippingAddress(User user);
	
}
