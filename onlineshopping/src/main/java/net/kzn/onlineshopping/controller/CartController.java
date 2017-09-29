package net.kzn.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.onlineshopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;
	
	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name="result",required=false)String result){
		ModelAndView modelAndView = new ModelAndView("page");
		if(result!=null){
		switch(result){
		case "updated":
			modelAndView.addObject("message","Cart line has been updated successfully!");
			break;
		case "added":
			modelAndView.addObject("message","Cart line has been added successfully!");
			break;
		case "deleted":
			modelAndView.addObject("message","Cart line has been deleted successfully");
			break;
		case "error":
			modelAndView.addObject("message","something went wrong!");
			break;
		}
		}
		
		modelAndView.addObject("title", "User Cart");
		modelAndView.addObject("userClickShowCart", "true");
		modelAndView.addObject("cartLines", cartService.getCartLine());
		return modelAndView;
	}
	
	@RequestMapping("/{cartLineId}/update")
	public String updateCart(@PathVariable int cartLineId,@RequestParam int count){
		String response = cartService.updateCartLine(cartLineId,count);
		
		return "redirect:/cart/show?"+response;
		
	}
	
	
	@RequestMapping("/{cartLineId}/delete")
	public String updateCart(@PathVariable int cartLineId){
		String response = cartService.deleteCartLine(cartLineId);
		
		return "redirect:/cart/show?"+response;
		
	}
	
	@RequestMapping("/add/{productId}/product")
	public String addCart(@PathVariable int productId){
		String response = cartService.addCartLine(productId);
		
		return "redirect:/cart/show?"+response;
		
	}
}
