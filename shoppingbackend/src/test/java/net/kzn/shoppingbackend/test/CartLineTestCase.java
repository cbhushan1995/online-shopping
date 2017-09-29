package net.kzn.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.shoppingbackend.dao.CartLineDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.CartLine;
import net.kzn.shoppingbackend.dto.Product;
import net.kzn.shoppingbackend.dto.User;

public class CartLineTestCase {

	
	private static AnnotationConfigApplicationContext context;
	
	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	
	
	private Product product =null;
	private User user;
	private Cart cart;
	private CartLine cartLine;
	
	@BeforeClass
	public static void init(){
		context=new AnnotationConfigApplicationContext();
		context.scan("net.kzn.shoppingbackend");
		context.refresh();
		productDAO =(ProductDAO) context.getBean("productDAO");
		cartLineDAO =(CartLineDAO) context.getBean("cartLineDAO");
		userDAO =(UserDAO) context.getBean("userDAO");
		
	}
	
	@Test
	public void testAddNewCartLine(){
		//1. get The User
		user = userDAO.getByEmail("ak@gmail.com");
		
		//2. fetch the cart
		cart = user.getCart();
		
		//3. get the product 
		product = productDAO.get(5);
		
		//4. create a cart line 
		cartLine = new CartLine();
		cartLine.setBuyingPrice(product.getUnitprice());
	
		cartLine.setProductCount(cartLine.getProductCount()+1);
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitprice());
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		
		assertEquals("Failed to add cart line ",true,cartLineDAO.add(cartLine));
		
		//update the cart
		cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
		cart.setCartLines(cart.getCartLines()+1);
		assertEquals("Failed to update cart",true,cartLineDAO.updateCart(cart));
	}
}
