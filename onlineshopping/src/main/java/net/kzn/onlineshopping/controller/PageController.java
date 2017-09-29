
package net.kzn.onlineshopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.onlineshopping.exception.ProductNotFoundException;
import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Category;
import net.kzn.shoppingbackend.dto.Product;

@Controller
public class PageController {
	private static final Logger logger=LoggerFactory.getLogger(PageController.class);
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;

	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("title", "HOME");
		
		logger.info("Inside pagecotroller index method -- INFO");
		logger.debug("Inside pagecotroller index method -- DEBUG");
		
		
//		passing the list of categories
		modelAndView.addObject("categories",categoryDAO.list());
		
		modelAndView.addObject("userClickHome", true);
		return modelAndView;
	}

	// @RequestMapping(value="/test")
	// //public ModelAndView test(@RequestParam("greeting")String
	// greetingVar)//greeting value is required in url
	// public ModelAndView
	// test(@RequestParam(value="greeting",required=false)String
	// greetingVar)//greeting value is not required in url
	// {
	// if(greetingVar==null)//if value is not coming from url
	// {
	// greetingVar="Hello there";
	// }
	// ModelAndView modelAndView = new ModelAndView("page");
	// modelAndView.addObject("greeting",greetingVar);
	// return modelAndView;
	// }

	// @RequestMapping(value="/test/{greeting}")//value for this greeting would
	// be dynamic
	// public ModelAndView test(@PathVariable("greeting")String
	// greetingVar)//greeting value is not required in url
	// {
	// if(greetingVar==null)//if value is not coming from url
	// {
	// greetingVar="Hello there";
	// }
	// ModelAndView modelAndView = new ModelAndView("page");
	// modelAndView.addObject("greeting",greetingVar);
	// return modelAndView;
	// }
	
	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("title", "About Us");
		modelAndView.addObject("userClickAbout", true);
		return modelAndView;
	}
	
	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("title", "Contact Us");
		modelAndView.addObject("userClickContact", true);
		return modelAndView;
	}
	
//	methods to load all the products and based on category
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("title", "All Products");
		
//		passing the list of categories
		modelAndView.addObject("categories",categoryDAO.list());
		
		modelAndView.addObject("userClickAllProducts", true);
		return modelAndView;
	}



@RequestMapping(value = "/show/category/{id}/products")
public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
	ModelAndView modelAndView = new ModelAndView("page");
	
	//categoryDAO to fetch a single category 
	Category category = null;
	category = categoryDAO.get(id);
	
	modelAndView.addObject("title", category.getName());
	
//	passing the list of categories
	modelAndView.addObject("categories",categoryDAO.list());
	//passing the single category object
	modelAndView.addObject("category",category);
	modelAndView.addObject("userClickCategoryProducts", true);
	return modelAndView;
}

/*
 * viewing the single product 
 *
 */

@RequestMapping(value="/show/{id}/product")
public ModelAndView showSingleProduct(@PathVariable int id)throws ProductNotFoundException
{
	ModelAndView mv=new ModelAndView("page");
	Product product=productDAO.get(id);
	
	if(product==null) throw new ProductNotFoundException();
	
	//update the view count
	product.setViews(product.getViews()+1);
	productDAO.update(product);
	//------------------------------------------------
	
	mv.addObject("Title", product.getName());
	mv.addObject("product", product);
	
	mv.addObject("userClickShowProduct", true);
	
	return mv;
}

//having similar mapping to our flow id
@RequestMapping(value="/register")
public ModelAndView register(){
	ModelAndView mv=new ModelAndView("page");
	mv.addObject("title","AboutUs");
	return mv;
}

/*Login */
@RequestMapping(value="/login")
public ModelAndView login(@RequestParam(name="error", required = false)String error,
		@RequestParam(name="logout", required = false)String logout){
	ModelAndView mv=new ModelAndView("login");
	if(error!=null){
		mv.addObject("message","Invalid Username and Password!");
	}
	if(logout!=null){
		mv.addObject("logout","User Has succefully logout");
	}
	mv.addObject("title","Login");
	return mv;
}



//access denied page
@RequestMapping(value="/access-denied")
public ModelAndView accessDenied(){
	ModelAndView mv=new ModelAndView("error");
	mv.addObject("title","403 - Access Denied");
	mv.addObject("errorTitle","Aha!	Caught You");
	mv.addObject("errorDescription","Your are not authorized to view this page!");
	return mv;
}

/*Logout */
@RequestMapping(value="/perform-logout")
public String logout(HttpServletRequest request,HttpServletResponse response){
	
	//first we are going to fetch the authentication object 
	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
	if(authentication!=null){
		new SecurityContextLogoutHandler().logout(request, response, authentication);
	}
	return "redirect:/login?logout";
	
}

}