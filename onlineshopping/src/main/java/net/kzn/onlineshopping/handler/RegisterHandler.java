package net.kzn.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import net.kzn.onlineshopping.model.RegisterModel;
import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;

@Component
public class RegisterHandler {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserDAO userDAO;
	public RegisterModel init(){
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel,User user){
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel,Address billing){
		registerModel.setBilling(billing);
	}
	
	public String saveAll(RegisterModel model){
		String transition="success";
		//fetch the user
		User user=model.getUser();
		if(user.getRole().equals("USER")){
			Cart cart=new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		//encode the password
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		//save the user
		userDAO.addUser(user);
		
		//get the address
			Address billing=model.getBilling();
			billing.setUserId(user.getId());
			billing.setBilling(true);
			//save the address
			userDAO.addAddress(billing);
		return transition;
	}
	
	
	public String validateUser(User user,MessageContext errors){
		String transitionValue="success";
		//checking if password matched confirm password
		 if(!(user.getPassword().equals(user.getConfirmPassword()))){
			 errors.addMessage(new MessageBuilder()
					 .error()
					 .source("confirmPassword")
					 .defaultText("password doesn't match the confirm password!")
					 .build());
			 transitionValue="failure";
			 
		 }
		
		 //check the uniqueness of the email id.
		 if(userDAO.getByEmail(user.getEmail())!=null){
			 errors.addMessage(new MessageBuilder()
					 .error()
					 .source("email")
					 .defaultText("Email address is already used!")
					 .build()); 
			 
			 transitionValue="failure";
		 }
		 
		 
		 
		return transitionValue;
	}
	
}
