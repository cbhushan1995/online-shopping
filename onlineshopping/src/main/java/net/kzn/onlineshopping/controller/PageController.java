
package net.kzn.onlineshopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("title", "HOME");
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
}
