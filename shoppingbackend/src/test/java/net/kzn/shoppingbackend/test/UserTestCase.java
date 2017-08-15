package net.kzn.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Address address = null;
	private Cart cart = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.kzn.shoppingbackend");
		context.refresh();

		userDAO = (UserDAO) context.getBean("userDAO");
	}

	// @Test
	// public void testAdd(){
	// user=new User();
	// user.setFirstName("Hrithik");
	// user.setLastName("Roshan");
	// user.setEmail("hr@gmail.com");
	// user.setContactNumber("1234567891234");
	// user.setRole("USER");
	// user.setPassword("123456");
	//
	// assertEquals("Failed to add user!",true,userDAO.addUser(user));
	//
	// address=new Address();
	// address.setAddressLineOne("101/B Jadoo society , krish nagar");
	// address.setAddressLineTwo("near kabil store");
	// address.setCity("Mumbai");
	// address.setState("Maharashtra");
	// address.setCountry("India");
	// address.setPostalCode("4000001");
	// address.setBilling(true);
	//
	// // link the user with the address using user id
	// address.setUserId(user.getId());
	// assertEquals("Failed to add address!", true,userDAO.addAddress(address));
	//
	// if(user.getRole().equals("USER")){
	// // create a cart for this user
	// cart= new Cart();
	// cart.setUser(user);
	// add the cart
	// assertEquals("Failed to add cart!", true,userDAO.addCart(cart));
	//
	// //add a shipping address for this User
	//
	// address=new Address();
	// address=new Address();
	// address.setAddressLineOne("101/B Jadoo society , krish nagar");
	// address.setAddressLineTwo("near kabil store");
	// address.setCity("Mumbai");
	// address.setState("Maharashtra");
	// address.setCountry("India");
	// address.setPostalCode("4000001");
	// //set shipping to be true
	// address.setShipping(true);
	// // link it with user
	// address.setUserId(user.getId());
	// assertEquals("Failed to add the shipping address!",
	// true,userDAO.addAddress(address));
	// }
	// }

//	@Test
//	public void testAdd() {
//		user = new User();
//		user.setFirstName("Hrithik");
//		user.setLastName("Roshan");
//		user.setEmail("hr@gmail.com");
//		user.setContactNumber("1234567891234");
//		user.setRole("USER");
//		user.setPassword("123456");
//		if (user.getRole().equals("USER")) {
//			// create a cart for this user
//			cart = new Cart();
//			cart.setUser(user);
//			// attach cart with the user
//			user.setCart(cart);
//		}
//		//add the user
//		assertEquals("Failed to add user!", true, userDAO.addUser(user));
//	}

	
//	@Test
//	public void testUpdateCart(){
//		//fetch the user by its email 
//		user=userDAO.getByEmail("hr@gmail.com");
//		
//		//get the cart of the user
//		cart = user.getCart();
//		cart.setGrandTotal(5555);
//		cart.setCartLines(2);
//		
//		assertEquals("failed to update the cart !",true,userDAO.updateCart(cart));
//	}
	
	@Test
	public void testAddAddress(){
		//we need to add an user
		
		 user=new User();
		 user.setFirstName("Hrithik");
		 user.setLastName("Roshan");
		 user.setEmail("hr@gmail.com");
		 user.setContactNumber("1234567891234");
		 user.setRole("USER");
		 user.setPassword("123456");
		
		 assertEquals("Failed to add user!",true,userDAO.addUser(user));
		
		 
		
		//we are going to add the address
		 address=new Address();
		 address.setAddressLineOne("101/B Jadoo society , krish nagar");
		 address.setAddressLineTwo("near ka bil store");
		 address.setCity("Mumbai");
		 address.setState("Maharashtra");
		 address.setCountry("India");
		 address.setPostalCode("4000001");
		 address.setBilling(true);
		//attach the user to the address
		 //address.setUser(user);
		 address.setUserId(user.getId());
		assertEquals("Failed to add address!", true, userDAO.addAddress(address));
		
		//we are going to add the shipping	 address
	}
}
