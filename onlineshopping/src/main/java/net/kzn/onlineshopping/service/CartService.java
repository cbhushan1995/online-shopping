package net.kzn.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kzn.onlineshopping.model.UserModel;
import net.kzn.shoppingbackend.dao.CartLineDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.CartLine;
import net.kzn.shoppingbackend.dto.Product;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private HttpSession httpSession;

	// return the cart of the user who has logged in.
	private Cart getCart() {

		return ((UserModel) httpSession.getAttribute("userModel")).getCart();
	}

	// returns the entire cart line
	public List<CartLine> getCartLine() {
		Cart cart = this.getCart();
		return cartLineDAO.list(cart.getId());
	}

	public String updateCartLine(int cartLineId, int count) {
		// TODO Auto-generated method stub
			//fetch the cartline
		CartLine cartline=cartLineDAO.get(cartLineId);
		if(cartline==null)
			return "resul=error";
		else
		{
			Product product=cartline.getProduct();
			double oldTotal=cartline.getTotal();
			cartline.setProductCount(count);
			if(product.getQuantity()<=count){
				count=product.getQuantity();
			}
			cartline.setProductCount(count);
			cartline.setBuyingPrice(product.getUnitprice());
			cartline.setTotal(product.getUnitprice()*count);
			cartLineDAO.update(cartline);
			Cart cart=this.getCart();
			cart.setGrandTotal(cart.getGrandTotal()-oldTotal+cartline.getTotal());
			cartLineDAO.updateCart(cart);
		return "result=updated";
		}
	}

	public String deleteCartLine(int cartLineId) {
		// TODO Auto-generated method stub
		// fetch the cart Line
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if (cartLine == null)
			return "result=error";
		else
		{		//update the cart
				Cart cart=this.getCart();
				cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
				cart.setCartLines(cart.getCartLines() - 1);
				cartLineDAO.updateCart(cart);
				
				//remove the cartline
				cartLineDAO.delete(cartLine);
				return "result=deleted";
		}
	}

	public String addCartLine(int productId) {
		String response=null;
		Cart cart = this.getCart();
		CartLine cartLine= cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		if(cartLine==null){
			cartLine = new CartLine();
			//fetch the product;
			Product product = productDAO.get(productId);
			//
			
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitprice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitprice());
			cartLine.setAvailable(true);
			
			cartLineDAO.add(cartLine);
			cart.setCartLines(cart.getCartLines()+1);
			cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			response="result=added";
		}
		return response;
	}

}
