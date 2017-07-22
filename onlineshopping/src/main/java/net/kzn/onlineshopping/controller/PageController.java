
package net.kzn.onlineshopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}